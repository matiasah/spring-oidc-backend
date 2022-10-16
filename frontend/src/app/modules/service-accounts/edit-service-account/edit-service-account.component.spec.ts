import {ComponentFixture, TestBed} from '@angular/core/testing';
import {EditServiceAccountComponent} from './edit-service-account.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {ServiceAccount} from "../../../interfaces/service-account";

describe('EditServiceAccountComponent', () => {
    let component: EditServiceAccountComponent;
    let fixture: ComponentFixture<EditServiceAccountComponent>;
    let serviceAccount: ServiceAccount = {} as any as ServiceAccount;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [EditServiceAccountComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                },
                {
                    provide: MAT_DIALOG_DATA,
                    useValue: serviceAccount
                }
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(EditServiceAccountComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
