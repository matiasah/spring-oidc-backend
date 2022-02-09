import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputAuthoritiesComponent } from './input-authorities.component';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
    declarations: [
        InputAuthoritiesComponent
    ],
    exports: [
        InputAuthoritiesComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class InputAuthoritiesModule { }
