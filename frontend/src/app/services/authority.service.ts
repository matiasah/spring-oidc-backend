import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Authority } from '../interfaces/authority';
import { Paginator, SpringPage } from '../util/paginator';

@Injectable({
    providedIn: 'root'
})
export class AuthorityService {

    public constructor(
        private http: HttpClient
    ) {

    }

    // Get Data Transfer Object (DTO)
    public getDTO(authority: Authority): Authority {
        return Object.assign({}, authority);
    }

    public paginator(): Paginator<Authority> {
        return new Paginator(this.http, `${environment.host}/authorities/page`);
    }

    public findTop10ByNameContaining(name: string): Observable<Authority[]> {
        return this.http.get<SpringPage<Authority>>(`${environment.host}/authorities/page?name=${name}&page=0&size=10`)
            .pipe(map(page => page.content));
    }

    public findAll(): Observable<Authority[]> {
        return this.http.get<Authority[]>(`${environment.host}/authorities`);
    }

    public findById(id: number): Observable<Authority> {
        return this.http.get<Authority>(`${environment.host}/authorities/${id}`);
    }

    public save(authority: Authority): Observable<Authority> {
        return this.http.post<Authority>(`${environment.host}/authorities`, this.getDTO(authority));
    }

    public update(authority: Authority): Observable<Authority> {
        return this.http.patch<Authority>(`${environment.host}/authorities/${authority.id}`, this.getDTO(authority));
    }

    public deleteById(id: string): Observable<void> {
        return this.http.delete<void>(`${environment.host}/authorities/${id}`);
    }

}
