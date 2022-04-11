import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthoritiesRoutingModule } from './authorities-routing.module';
import { AuthoritiesComponent } from './authorities/authorities.component';
import { CreateAuthorityComponent } from './create-authority/create-authority.component';
import { EditAuthorityComponent } from './edit-authority/edit-authority.component';
import { DeleteAuthorityComponent } from './delete-authority/delete-authority.component';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputTagsModule } from "../input-tags/input-tags.module";

@NgModule({
    declarations: [
        AuthoritiesComponent,
        CreateAuthorityComponent,
        EditAuthorityComponent,
        DeleteAuthorityComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
        AuthoritiesRoutingModule,
        InputTagsModule
    ]
})
export class AuthoritiesModule { }
