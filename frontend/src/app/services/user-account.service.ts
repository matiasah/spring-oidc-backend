import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserAccount } from '../interfaces/user-account';
import { Paginator } from '../util/paginator';

@Injectable({
    providedIn: 'root'
})
export class UserAccountService {

    public constructor(
        private http: HttpClient
    ) {

    }

    // Get Data Transfer Object (DTO)
    public getDTO(userAccount: UserAccount): UserAccount {
        return Object.assign({}, userAccount);
    }

    public paginator(): Paginator<UserAccount> {
        return new Paginator(this.http, `${environment.host}/user-accounts/page`);
    }

    public findAll(): Observable<UserAccount[]> {
        return this.http.get<UserAccount[]>(`${environment.host}/user-accounts`);
    }

    public findById(id: number): Observable<UserAccount> {
        return this.http.get<UserAccount>(`${environment.host}/user-accounts/${id}`);
    }

    public save(userAccount: UserAccount): Observable<UserAccount> {
        return this.http.post<UserAccount>(`${environment.host}/user-accounts`, this.getDTO(userAccount));
    }

    public update(userAccount: UserAccount): Observable<UserAccount> {
        return this.http.patch<UserAccount>(`${environment.host}/user-accounts/${userAccount.id}`, this.getDTO(userAccount));
    }

    public deleteById(id: number): Observable<void> {
        return this.http.delete<void>(`${environment.host}/user-accounts/${id}`);
    }

}
