import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ScopesComponent } from './scopes/scopes.component';

const routes: Routes = [
    {
        path: '',
        component: ScopesComponent
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ScopesRoutingModule { }
