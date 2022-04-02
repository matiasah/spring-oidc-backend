import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserAccountsRoutingModule } from './user-accounts-routing.module';
import { UserAccountsComponent } from './user-accounts/user-accounts.component';
import { MaterialModule } from '../material/material.module';
import { CreateUserAccountComponent } from './create-user-account/create-user-account.component';
import { EditUserAccountComponent } from './edit-user-account/edit-user-account.component';
import { DeleteUserAccountComponent } from './delete-user-account/delete-user-account.component';
import { InputAuthoritiesModule } from '../input-authorities/input-authorities.module';
import {InputTagsModule} from "../input-tags/input-tags.module";

@NgModule({
    declarations: [
        UserAccountsComponent,
        CreateUserAccountComponent,
        EditUserAccountComponent,
        DeleteUserAccountComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
        InputAuthoritiesModule,
        InputTagsModule,
        UserAccountsRoutingModule
    ]
})
export class UserAccountsModule { }
