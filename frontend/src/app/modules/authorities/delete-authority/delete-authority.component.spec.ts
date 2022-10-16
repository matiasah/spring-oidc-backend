import {ComponentFixture, TestBed} from '@angular/core/testing';
import {DeleteAuthorityComponent} from './delete-authority.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {MaterialModule} from "../../material/material.module";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('DeleteAuthorityComponent', () => {
    let component: DeleteAuthorityComponent;
    let fixture: ComponentFixture<DeleteAuthorityComponent>;

    beforeEach(async (compileComponents) => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                MaterialModule
            ],
            declarations: [DeleteAuthorityComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(DeleteAuthorityComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
