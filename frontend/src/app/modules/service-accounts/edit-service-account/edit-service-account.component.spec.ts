import {ComponentFixture, TestBed} from '@angular/core/testing';
import {EditServiceAccountComponent} from './edit-service-account.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {ServiceAccount} from "../../../interfaces/service-account";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputAuthoritiesModule} from "../../input-authorities/input-authorities.module";
import {InputTagsModule} from "../../input-tags/input-tags.module";
import {InputScopesTableModule} from "../../input-scopes-table/input-scopes-table.module";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('EditServiceAccountComponent', () => {
    let component: EditServiceAccountComponent;
    let fixture: ComponentFixture<EditServiceAccountComponent>;
    let serviceAccount: ServiceAccount = {} as any as ServiceAccount;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [EditServiceAccountComponent],
    imports: [ReactiveFormsModule,
        FormsModule,
        NoopAnimationsModule,
        MaterialModule,
        InputAuthoritiesModule,
        InputTagsModule,
        InputScopesTableModule],
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
        fixture = TestBed.createComponent(EditServiceAccountComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
