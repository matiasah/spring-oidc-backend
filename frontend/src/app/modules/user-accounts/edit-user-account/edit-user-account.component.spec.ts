import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EditUserAccountComponent} from './edit-user-account.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {UserAccount} from "../../../interfaces/user-account";

describe('EditUserAccountComponent', () => {
    let component: EditUserAccountComponent;
    let fixture: ComponentFixture<EditUserAccountComponent>;
    let userAccount: UserAccount = {} as any as UserAccount;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                NoopAnimationsModule,
                MaterialModule,
                MatSnackBarModule
            ],
            declarations: [EditUserAccountComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                },
                {
                    provide: MAT_DIALOG_DATA,
                    useValue: userAccount
                }
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(EditUserAccountComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
