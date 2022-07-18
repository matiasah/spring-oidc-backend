import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuditEventsComponent} from "./audit-events/audit-events.component";

const routes: Routes = [
    {
        path: '',
        component: AuditEventsComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AuditEventsRoutingModule {
}
