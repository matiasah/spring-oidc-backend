import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserAccountsRoutingModule } from './user-accounts-routing.module';
import { UserAccountsComponent } from './user-accounts/user-accounts.component';
import { MaterialModule } from '../material/material.module';
import { CreateUserAccountComponent } from './create-user-account/create-user-account.component';
import { EditUserAccountComponent } from './edit-user-account/edit-user-account.component';
import { DeleteUserAccountComponent } from './delete-user-account/delete-user-account.component';

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
        UserAccountsRoutingModule
    ]
})
export class UserAccountsModule { }
