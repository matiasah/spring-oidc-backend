import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthorizationInfo } from '../interfaces/authorization-info';

@Injectable({
    providedIn: 'root'
})
export class AuthorizationInfoService {

    public constructor(
        private http: HttpClient
    ) { }

    /**
     * Get authorization info from the server.
     * 
     * @param scope The scope to get authorization info for.
     * @param clientId The client id to get authorization info for.
     * @param state The state to get authorization info for.
     * @returns The authorization info.
    */
    public getAuthorizationInfo(scope: string, clientId: string, state: string): Observable<AuthorizationInfo> {
        // Initialize HTTP Params
        const params: HttpParams = new HttpParams()
            .set('scope', scope)
            .set('client_id', clientId)
            .set('state', state);

        // Send request and return response
        return this.http.get<AuthorizationInfo>(`${environment.host}/oauth2/authorization-info`, { params: params, withCredentials: true });
    }

}
