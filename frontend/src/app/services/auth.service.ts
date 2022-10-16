import { HttpClient, HttpHeaders, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AccessToken } from '../interfaces/access-token';
import { catchError, map } from 'rxjs/operators';
import { Router } from '@angular/router';

function setTokenExpiresAt(accessToken: AccessToken): AccessToken {

    // Set expiresAt
    accessToken.expiresAt = new Date(Date.now() + accessToken.expires_in * 1000);

    // Return accessToken
    return accessToken;
}

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    // Access token
    private token?: AccessToken;

    public constructor(
        private storage: Storage,
        private router: Router,
        private http: HttpClient,
        private window: Window
    ) {

        // Si hay token en localStorage
        const item: string | null = this.storage.getItem(environment.token_item);

        // Si el item existe
        if (item) {
            // Leer objeto
            this.token = JSON.parse(item);

            // Si hay token
            if (this.token) {

                // Si hay expiracion
                if (this.token.expiresAt) {
                    // Transformar fecha a Date
                    this.token.expiresAt = new Date(this.token.expiresAt);
                } else {
                    // Eliminar token
                    this.logout();
                }
            }
        }
    }

    public login(): void {
        // HTTP Params
        const httpParams: HttpParams = new HttpParams()
            .set('client_id', environment.clientId)
            .set('redirect_uri', environment.redirectUri)
            .set('scope', environment.scope)
            .set('response_type', 'code');

        // HTTP Request
        const httpRequest: HttpRequest<unknown> = new HttpRequest('GET', `${environment.host}/oauth2/authorize`, { params: httpParams });

        // Redirect to login
        this.window.location = httpRequest.urlWithParams as any as Location;
    }

    public exchangeCodeForToken(code: string): Observable<AccessToken> {
        // Request body
        const body: URLSearchParams = new URLSearchParams();

        body.append('code', code);
        body.append('grant_type', 'authorization_code');
        body.append('redirect_uri', environment.redirectUri);

        // Basic token
        const basicToken: string = btoa(`${environment.clientId}:${environment.clientSecret}`);

        // Headers
        const headers: HttpHeaders = new HttpHeaders({
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': `Basic ${basicToken}`
        });

        // Get token
        return this.http.post<AccessToken>(`${environment.host}/oauth2/token`, body, { headers, withCredentials: true })
            .pipe(map(setTokenExpiresAt));
    }

    public exchangeRefreshTokenForAccessToken(refreshToken: string): Observable<AccessToken> {
        // Request body
        const body: URLSearchParams = new URLSearchParams()

        body.append('grant_type', 'refresh_token');
        body.append('refresh_token', refreshToken);
        body.append('scope', environment.scope);

        // Basic token
        const basicToken: string = btoa(`${environment.clientId}:${environment.clientSecret}`);

        // Headers
        const headers: HttpHeaders = new HttpHeaders({
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': `Basic ${basicToken}`
        });

        // Get token
        return this.http.post<AccessToken>(`${environment.host}/oauth2/token`, body, { headers, withCredentials: true })
            .pipe(map(setTokenExpiresAt));
    }

    public getToken(): AccessToken | undefined {
        // If there's a token in memory
        if (this.token) {

            // Return a copy of the token
            return Object.assign({}, this.token);
        }

        // Return undefined
        return undefined;
    }

    public setToken(token: AccessToken): AccessToken | undefined {
        // If the token is valid
        if (token != null) {

            // Copy the token
            this.token = Object.assign({}, token);

            // Put in localStorage
            this.storage.setItem(environment.token_item, JSON.stringify(this.token));

            // Return a copy of the token
            return Object.assign({}, this.token);
        }

        // Return undefined
        return undefined;
    }

    public logout(): void {
        // Remove token from memory
        this.token = undefined;

        // Remove token from localStorage
        this.storage.removeItem(environment.token_item);

        // Perform logout
        this.http.get(`${environment.host}/logout`, { responseType: 'text', withCredentials: true }).subscribe();

        // Go root page
        this.router.navigate(['/']);
    }

    public isAuthenticated(): Observable<boolean> {
        // If there's a token in memory
        if (this.token) {

            // If the token hasn't expired
            if (this.token.expiresAt && this.token.expiresAt.getTime() > Date.now()) {

                // Return true
                return of(true);

            }

            // If there's a refresh token
            if (this.token.refresh_token) {

                // Request new token
                return this.exchangeRefreshTokenForAccessToken(this.token.refresh_token)
                    // Set expiresAt
                    .pipe(map(setTokenExpiresAt))
                    // Map response
                    .pipe(map(
                        accessToken => {

                            // Set token
                            this.setToken(accessToken);

                            // Return true
                            return true;
                        }
                    ))
                    // If an error was caught on the request
                    .pipe(catchError(
                        error => {

                            // Erase token
                            this.logout();

                            // Return false
                            return of(false);
                        }
                    ));

            }

        }

        // Token is not valid, return false
        return of(false);
    }

}
