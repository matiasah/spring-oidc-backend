import {ComponentFixture, TestBed} from '@angular/core/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {AppComponent} from './app.component';
import {MaterialModule} from "./modules/material/material.module";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('AppComponent', () => {

    let fixture: ComponentFixture<AppComponent>;
    let app: AppComponent;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                MaterialModule,
                HttpClientTestingModule
            ],
            declarations: [
                AppComponent
            ],
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(AppComponent);
        app = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(app).toBeTruthy();
    });

});
