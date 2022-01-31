import { HttpClient, HttpHeaders, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AccessToken } from '../models/access-token';
import { User } from '../models/user';
import { map, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    // Access token
    private token?: AccessToken;

    public constructor(
        private storage: Storage,
        private router: Router,
        private http: HttpClient
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
                    this.cleanToken();
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
        window.location = httpRequest.urlWithParams as any as Location;
    }

    public exchangeCodeForToken(code: string): Observable<AccessToken> {
        // Request body
        const body: URLSearchParams = new URLSearchParams()

        body.append('code', code)
        body.append('grant_type', 'authorization_code')
        body.append('redirect_uri', environment.redirectUri)

        // Basic token
        const basicToken: string = btoa(`${environment.clientId}:${environment.clientSecret}`);

        // Headers
        const headers: HttpHeaders = new HttpHeaders({
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': `Basic ${basicToken}`
        });

        // Get token
        return this.http.post<AccessToken>(`${environment.host}/oauth2/token`, body, { headers, withCredentials: true })
            .pipe(map(
                (accessToken) => {
                    // Set expiresAt
                    accessToken.expiresAt = new Date(Date.now() + accessToken.expires_in * 1000);

                    // Return accessToken
                    return accessToken;
                }
            ));
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

    public cleanToken(): void {
        // Remove token from memory
        this.token = undefined;

        // Remove token from localStorage
        this.storage.removeItem(environment.token_item);
    }

    public isAuthenticated(): boolean {
        // If there's a token in memory
        if (this.token && this.token.expiresAt) {
            // Verify that the token is still valid
            return this.token.expiresAt.getTime() > Date.now();
        }

        // Token is not valid, return false
        return false;
    }

}
