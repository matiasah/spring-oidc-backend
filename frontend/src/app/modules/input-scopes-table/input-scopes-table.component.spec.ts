import {ComponentFixture, TestBed} from '@angular/core/testing';
import {InputScopesTableComponent} from './input-scopes-table.component';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {MaterialModule} from "../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('InputScopesTableComponent', () => {
    let component: InputScopesTableComponent;
    let fixture: ComponentFixture<InputScopesTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [InputScopesTableComponent],
    imports: [ReactiveFormsModule,
        FormsModule,
        NoopAnimationsModule,
        MaterialModule],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
}).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(InputScopesTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
