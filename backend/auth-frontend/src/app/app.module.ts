import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { MaterialModule } from './modules/material/material.module';

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        MaterialModule,
        AuthenticationModule
    ],
    providers: [
        {
            provide: Storage,
            useValue: localStorage
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
