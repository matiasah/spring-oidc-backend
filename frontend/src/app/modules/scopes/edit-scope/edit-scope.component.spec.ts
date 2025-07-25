import {ComponentFixture, TestBed} from '@angular/core/testing';
import {EditScopeComponent} from './edit-scope.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {MaterialModule} from "../../material/material.module";
import {Scope} from "../../../interfaces/scope";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTagsModule} from "../../input-tags/input-tags.module";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('EditScopeComponent', () => {
    let component: EditScopeComponent;
    let fixture: ComponentFixture<EditScopeComponent>;
    let scope: Scope = {} as any as Scope;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [
        EditScopeComponent,
    ],
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
            useValue: scope
        },
        provideHttpClient(withInterceptorsFromDi()),
        provideHttpClientTesting()
    ]
}).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(EditScopeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
