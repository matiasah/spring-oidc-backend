import {ComponentFixture, TestBed} from '@angular/core/testing';
import {DeleteScopeComponent} from './delete-scope.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {MaterialModule} from "../../material/material.module";

describe('DeleteScopeComponent', () => {
    let component: DeleteScopeComponent;
    let fixture: ComponentFixture<DeleteScopeComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                MaterialModule
            ],
            declarations: [DeleteScopeComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(DeleteScopeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
