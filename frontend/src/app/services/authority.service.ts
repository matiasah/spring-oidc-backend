import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Authority } from '../interfaces/authority';
import { Paginator } from '../util/paginator';

@Injectable({
    providedIn: 'root'
})
export class AuthorityService {

    public constructor(
        private http: HttpClient
    ) {

    }

    // Get Data Transfer Object (DTO)
    public getDTO(serviceAccount: Authority): Authority {
        return Object.assign({}, serviceAccount);
    }

    public paginator(): Paginator<Authority> {
        return new Paginator(this.http, `${environment.host}/authorities/page`);
    }

    public findAll(): Observable<Authority[]> {
        return this.http.get<Authority[]>(`${environment.host}/authorities`);
    }

    public findById(id: number): Observable<Authority> {
        return this.http.get<Authority>(`${environment.host}/authorities/${id}`);
    }

    public save(authorities: Authority): Observable<Authority> {
        return this.http.post<Authority>(`${environment.host}/authorities`, this.getDTO(authorities));
    }

    public update(authorities: Authority): Observable<Authority> {
        return this.http.patch<Authority>(`${environment.host}/authorities/${authorities.id}`, this.getDTO(authorities));
    }

    public deleteById(id: string): Observable<void> {
        return this.http.delete<void>(`${environment.host}/authorities/${id}`);
    }

}
