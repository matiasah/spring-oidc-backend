import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationGuard } from './guards/authentication.guard';
import { SystemGuard } from './guards/system.guard';

const routes: Routes = [
    {
        path: '',
        loadChildren: () => import('./modules/authentication/authentication.module').then(m => m.AuthenticationModule),
        canActivate: [AuthenticationGuard]
    },
    {
        path: 'sys',
        loadChildren: () => import('./modules/system/system.module').then(m => m.SystemModule),
        canActivate: [SystemGuard]
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
