import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserAccountsComponent } from '../user-accounts/user-accounts/user-accounts.component';
import { SystemComponent } from './system/system.component';

const routes: Routes = [
    {
        path: '',
        component: SystemComponent
    },
    {
        path: 'user-accounts',
        component: UserAccountsComponent
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SystemRoutingModule { }
