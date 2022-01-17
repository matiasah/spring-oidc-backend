import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthenticationRoutingModule } from './authentication-routing.module';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { AuthorizeComponent } from './authorize/authorize.component';

@NgModule({
    declarations: [
        LoginComponent,
        AuthorizeComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MaterialModule,
        AuthenticationRoutingModule
    ]
})
export class AuthenticationModule { }
