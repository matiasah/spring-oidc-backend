import {ComponentFixture, TestBed} from '@angular/core/testing';
import {EditScopeComponent} from './edit-scope.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {MaterialModule} from "../../material/material.module";
import {Scope} from "../../../interfaces/scope";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('EditScopeComponent', () => {
    let component: EditScopeComponent;
    let fixture: ComponentFixture<EditScopeComponent>;
    let scope: Scope = {} as any as Scope;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                MaterialModule
            ],
            declarations: [EditScopeComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                },
                {
                    provide: MAT_DIALOG_DATA,
                    useValue: scope
                }
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
