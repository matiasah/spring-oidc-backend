import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DeleteServiceAccountComponent} from './delete-service-account.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {MaterialModule} from "../../material/material.module";
import {ServiceAccount} from "../../../interfaces/service-account";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('DeleteServiceAccountComponent', () => {
    let component: DeleteServiceAccountComponent;
    let fixture: ComponentFixture<DeleteServiceAccountComponent>;
    let serviceAccount: ServiceAccount = {} as any as ServiceAccount;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [DeleteServiceAccountComponent],
    imports: [MaterialModule],
    providers: [
        {
            provide: MatDialogRef,
            useValue: new TestMatDialogRef()
        },
        {
            provide: MAT_DIALOG_DATA,
            useValue: serviceAccount
        },
        provideHttpClient(withInterceptorsFromDi()),
        provideHttpClientTesting()
    ]
}).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(DeleteServiceAccountComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
