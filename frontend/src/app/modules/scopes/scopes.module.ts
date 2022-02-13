import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ScopesRoutingModule } from './scopes-routing.module';
import { ScopesComponent } from './scopes/scopes.component';
import { CreateScopeComponent } from './create-scope/create-scope.component';
import { EditScopeComponent } from './edit-scope/edit-scope.component';
import { DeleteScopeComponent } from './delete-scope/delete-scope.component';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
    declarations: [
        ScopesComponent,
        CreateScopeComponent,
        EditScopeComponent,
        DeleteScopeComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
        ScopesRoutingModule
    ]
})
export class ScopesModule { }
