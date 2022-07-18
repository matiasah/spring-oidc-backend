import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Paginator} from "../util/paginator";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {AuditEvent} from "../interfaces/audit-event";

@Injectable({
    providedIn: 'root'
})
export class AuditEventService {

    public constructor(
        private http: HttpClient
    ) {

    }

    // Get Data Transfer Object (DTO)
    public getDTO(auditEvent: AuditEvent): AuditEvent {
        return Object.assign({}, auditEvent);
    }

    public paginator(): Paginator<AuditEvent> {
        return new Paginator(this.http, `${environment.host}/audit-events/page`);
    }

    public findAll(): Observable<AuditEvent[]> {
        return this.http.get<AuditEvent[]>(`${environment.host}/audit-events`);
    }

    public findById(id: number): Observable<AuditEvent> {
        return this.http.get<AuditEvent>(`${environment.host}/audit-events/${id}`);
    }

}
