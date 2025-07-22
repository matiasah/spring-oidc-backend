import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { MaterialModule } from './modules/material/material.module';

@NgModule({ declarations: [
        AppComponent
    ],
    bootstrap: [AppComponent], imports: [BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MaterialModule,
        AuthenticationModule], providers: [
        {
            provide: Storage,
            useValue: localStorage
        },
        provideHttpClient(withInterceptorsFromDi())
    ] })
export class AppModule { }
