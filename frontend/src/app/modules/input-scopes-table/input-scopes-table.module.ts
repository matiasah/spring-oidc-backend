import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputScopesTableComponent } from './input-scopes-table.component';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
    declarations: [
        InputScopesTableComponent
    ],
    exports: [
        InputScopesTableComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class InputScopesTableModule { }
