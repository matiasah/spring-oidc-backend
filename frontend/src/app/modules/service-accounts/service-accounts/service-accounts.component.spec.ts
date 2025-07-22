import {ComponentFixture, TestBed} from '@angular/core/testing';
import {ServiceAccountsComponent} from './service-accounts.component';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import {MaterialModule} from "../../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('ServiceAccountsComponent', () => {
    let component: ServiceAccountsComponent;
    let fixture: ComponentFixture<ServiceAccountsComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [ServiceAccountsComponent],
    imports: [ReactiveFormsModule,
        FormsModule,
        NoopAnimationsModule,
        MaterialModule],
    providers: [
        {
            provide: MatDialogRef,
            useValue: new TestMatDialogRef()
        },
        provideHttpClient(withInterceptorsFromDi()),
        provideHttpClientTesting()
    ]
}).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ServiceAccountsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
