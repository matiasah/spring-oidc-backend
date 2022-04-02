import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputTagsComponent } from './input-tags.component';
import { MaterialModule } from "../material/material.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@NgModule({
    declarations: [
        InputTagsComponent
    ],
    exports: [
        InputTagsComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class InputTagsModule { }
