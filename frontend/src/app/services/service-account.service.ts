import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ServiceAccount } from '../interfaces/service-account';
import { Paginator } from '../util/paginator';

@Injectable({
    providedIn: 'root'
})
export class ServiceAccountService {

    public constructor(
        private http: HttpClient
    ) {

    }

    // Get Data Transfer Object (DTO)
    public getDTO(serviceAccount: ServiceAccount): ServiceAccount {
        return Object.assign({}, serviceAccount);
    }

    public paginator(): Paginator<ServiceAccount> {
        return new Paginator(this.http, `${environment.host}/service-accounts/page`);
    }

    public findAll(): Observable<ServiceAccount[]> {
        return this.http.get<ServiceAccount[]>(`${environment.host}/service-accounts`);
    }

    public findById(id: number): Observable<ServiceAccount> {
        return this.http.get<ServiceAccount>(`${environment.host}/service-accounts/${id}`);
    }

    public save(serviceAccount: ServiceAccount): Observable<ServiceAccount> {
        return this.http.post<ServiceAccount>(`${environment.host}/service-accounts`, this.getDTO(serviceAccount));
    }

    public update(serviceAccount: ServiceAccount): Observable<ServiceAccount> {
        return this.http.patch<ServiceAccount>(`${environment.host}/service-accounts/${serviceAccount.id}`, this.getDTO(serviceAccount));
    }

    public deleteById(id: number): Observable<void> {
        return this.http.delete<void>(`${environment.host}/service-accounts/${id}`);
    }

}
