import {ComponentFixture, TestBed} from '@angular/core/testing';
import {DeleteUserAccountComponent} from './delete-user-account.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { UserAccount } from 'src/app/interfaces/user-account';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('DeleteUserAccountComponent', () => {
    let component: DeleteUserAccountComponent;
    let fixture: ComponentFixture<DeleteUserAccountComponent>;
    let userAccount: UserAccount = {} as any as UserAccount;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [DeleteUserAccountComponent],
    imports: [NoopAnimationsModule,
        MaterialModule],
    providers: [
        {
            provide: MatDialogRef,
            useValue: new TestMatDialogRef()
        },
        {
            provide: MAT_DIALOG_DATA,
            useValue: userAccount
        },
        provideHttpClient(withInterceptorsFromDi()),
        provideHttpClientTesting()
    ]
}).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(DeleteUserAccountComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
