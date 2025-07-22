import {ComponentFixture, TestBed} from '@angular/core/testing';
import {EditAuthorityComponent} from './edit-authority.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {MaterialModule} from "../../material/material.module";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {Authority} from "../../../interfaces/authority";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTagsModule} from "../../input-tags/input-tags.module";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('EditAuthorityComponent', () => {
    let component: EditAuthorityComponent;
    let fixture: ComponentFixture<EditAuthorityComponent>;
    let authority: Authority = {} as any as Authority;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [EditAuthorityComponent],
    imports: [ReactiveFormsModule,
        FormsModule,
        NoopAnimationsModule,
        MaterialModule,
        InputTagsModule],
    providers: [
        {
            provide: MatDialogRef,
            useValue: new TestMatDialogRef()
        },
        {
            provide: MAT_DIALOG_DATA,
            useValue: authority
        },
        provideHttpClient(withInterceptorsFromDi()),
        provideHttpClientTesting()
    ]
}).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(EditAuthorityComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
