import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthoritiesComponent } from './authorities/authorities.component';

const routes: Routes = [
    {
        path: '',
        component: AuthoritiesComponent
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthoritiesRoutingModule { }
