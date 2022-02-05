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
            }
        ]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SystemRoutingModule { }
