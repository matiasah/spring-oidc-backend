import {ComponentFixture, TestBed} from '@angular/core/testing';
import {EditAuthorityComponent} from './edit-authority.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {MaterialModule} from "../../material/material.module";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {Authority} from "../../../interfaces/authority";

describe('EditAuthorityComponent', () => {
    let component: EditAuthorityComponent;
    let fixture: ComponentFixture<EditAuthorityComponent>;
    let authority: Authority = {} as any as Authority;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                MaterialModule
            ],
            declarations: [EditAuthorityComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                },
                {
                    provide: MAT_DIALOG_DATA,
                    useValue: authority
                }
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
