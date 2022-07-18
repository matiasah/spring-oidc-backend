import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SystemComponent } from './system/system.component';

const routes: Routes = [
    {
        path: '',
        component: SystemComponent,
        children: [
            {
                path: 'user-accounts',
                loadChildren: () => import('../user-accounts/user-accounts.module').then(m => m.UserAccountsModule)
            },
            {
                path: 'service-accounts',
                loadChildren: () => import('../service-accounts/service-accounts.module').then(m => m.ServiceAccountsModule)
            },
            {
                path: 'authorities',
                loadChildren: () => import('../authorities/authorities.module').then(m => m.AuthoritiesModule)
            },
            {
                path: 'scopes',
                loadChildren: () => import('../scopes/scopes.module').then(m => m.ScopesModule)
            },
            {
                path: 'audit-events',
                loadChildren: () => import('../audit-events/audit-events.module').then(m => m.AuditEventsModule)
            }
        ]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SystemRoutingModule { }
