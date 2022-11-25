import {ComponentFixture, TestBed} from '@angular/core/testing';
import {ServiceAccountsComponent} from './service-accounts.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatLegacyDialogRef as MatDialogRef} from "@angular/material/legacy-dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import {MaterialModule} from "../../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

describe('ServiceAccountsComponent', () => {
    let component: ServiceAccountsComponent;
    let fixture: ComponentFixture<ServiceAccountsComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                ReactiveFormsModule,
                FormsModule,
                NoopAnimationsModule,
                MaterialModule
            ],
            declarations: [ServiceAccountsComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
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
