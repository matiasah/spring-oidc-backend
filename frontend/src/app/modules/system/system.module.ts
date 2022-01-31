import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SystemRoutingModule } from './system-routing.module';
import { SystemComponent } from './system/system.component';
import { MaterialModule } from '../material/material.module';

@NgModule({
    declarations: [
        SystemComponent
    ],
    imports: [
        CommonModule,
        SystemRoutingModule,
        MaterialModule
    ]
})
export class SystemModule { }
