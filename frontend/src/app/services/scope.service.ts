import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Scope } from '../interfaces/scope';
import { Paginator } from '../util/paginator';

@Injectable({
  providedIn: 'root'
})
export class ScopeService {

    public constructor(
        private http: HttpClient
    ) {

    }

    // Get Data Transfer Object (DTO)
    public getDTO(serviceAccount: Scope): Scope {
        return Object.assign({}, serviceAccount);
    }

    public paginator(): Paginator<Scope> {
        return new Paginator(this.http, `${environment.host}/scopes/page`);
    }

    public findAll(): Observable<Scope[]> {
        return this.http.get<Scope[]>(`${environment.host}/scopes`);
    }

    public findById(id: number): Observable<Scope> {
        return this.http.get<Scope>(`${environment.host}/scopes/${id}`);
    }

    public save(scope: Scope): Observable<Scope> {
        return this.http.post<Scope>(`${environment.host}/scopes`, this.getDTO(scope));
    }

    public update(scope: Scope): Observable<Scope> {
        return this.http.patch<Scope>(`${environment.host}/scopes/${scope.id}`, this.getDTO(scope));
    }

    public deleteById(id: string): Observable<void> {
        return this.http.delete<void>(`${environment.host}/scopes/${id}`);
    }

}
