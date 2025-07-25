import {ComponentFixture, TestBed} from '@angular/core/testing';
import {EditUserAccountComponent} from './edit-user-account.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {UserAccount} from "../../../interfaces/user-account";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTagsModule} from "../../input-tags/input-tags.module";
import {InputAuthoritiesModule} from "../../input-authorities/input-authorities.module";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('EditUserAccountComponent', () => {
    let component: EditUserAccountComponent;
    let fixture: ComponentFixture<EditUserAccountComponent>;
    let userAccount: UserAccount = {
        authorities: []
    } as any as UserAccount;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [EditUserAccountComponent],
    imports: [ReactiveFormsModule,
        FormsModule,
        NoopAnimationsModule,
        MaterialModule,
        InputAuthoritiesModule,
        InputTagsModule],
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
        fixture = TestBed.createComponent(EditUserAccountComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
