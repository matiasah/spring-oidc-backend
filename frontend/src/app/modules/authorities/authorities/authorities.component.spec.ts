import {ComponentFixture, TestBed, waitForAsync} from '@angular/core/testing';
import {AuthoritiesComponent} from './authorities.component';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {MaterialModule} from "../../material/material.module";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('AuthoritiesComponent', () => {
    let component: AuthoritiesComponent;
    let fixture: ComponentFixture<AuthoritiesComponent>;

    beforeEach(async  () => {
        await TestBed.configureTestingModule({
    declarations: [AuthoritiesComponent],
    imports: [ReactiveFormsModule,
        FormsModule,
        NoopAnimationsModule,
        MaterialModule],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
}).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(AuthoritiesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
