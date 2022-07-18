import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuditEventsRoutingModule} from './audit-events-routing.module';
import {AuditEventsComponent} from './audit-events/audit-events.component';
import {MaterialModule} from "../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
    declarations: [
        AuditEventsComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
        AuditEventsRoutingModule
    ]
})
export class AuditEventsModule {
}
