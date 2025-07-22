import {ComponentFixture, TestBed} from '@angular/core/testing';
import {InputAuthoritiesComponent} from './input-authorities.component';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {MaterialModule} from "../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('InputAuthoritiesComponent', () => {
    let component: InputAuthoritiesComponent;
    let fixture: ComponentFixture<InputAuthoritiesComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [InputAuthoritiesComponent],
    imports: [ReactiveFormsModule,
        FormsModule,
        NoopAnimationsModule,
        MaterialModule],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
}).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(InputAuthoritiesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
