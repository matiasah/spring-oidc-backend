import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServiceAccountsRoutingModule } from './service-accounts-routing.module';
import { ServiceAccountsComponent } from './service-accounts/service-accounts.component';
import { CreateServiceAccountComponent } from './create-service-account/create-service-account.component';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditServiceAccountComponent } from './edit-service-account/edit-service-account.component';
import { DeleteServiceAccountComponent } from './delete-service-account/delete-service-account.component';
import { InputAuthoritiesModule } from '../input-authorities/input-authorities.module';
import { InputScopesTableModule } from '../input-scopes-table/input-scopes-table.module';

@NgModule({
    declarations: [
        ServiceAccountsComponent,
        CreateServiceAccountComponent,
        EditServiceAccountComponent,
        DeleteServiceAccountComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
        InputAuthoritiesModule,
        InputScopesTableModule,
        ServiceAccountsRoutingModule
    ]
})
export class ServiceAccountsModule { }
