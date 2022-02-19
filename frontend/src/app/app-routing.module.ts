import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SystemGuard } from './guards/system.guard';

const routes: Routes = [
    {
        path: '',
        loadChildren: () => import('./modules/authentication/authentication.module').then(m => m.AuthenticationModule)
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
