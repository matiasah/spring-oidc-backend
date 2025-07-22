import {ComponentFixture, TestBed, waitForAsync} from '@angular/core/testing';
import {ScopesComponent} from './scopes.component';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('ScopesComponent', () => {
    let component: ScopesComponent;
    let fixture: ComponentFixture<ScopesComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [ScopesComponent],
    imports: [ReactiveFormsModule,
        FormsModule,
        NoopAnimationsModule,
        MaterialModule],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
}).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ScopesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
