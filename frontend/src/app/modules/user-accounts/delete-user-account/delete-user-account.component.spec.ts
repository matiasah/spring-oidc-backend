import {ComponentFixture, TestBed} from '@angular/core/testing';
import {DeleteUserAccountComponent} from './delete-user-account.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";

describe('DeleteUserAccountComponent', () => {
    let component: DeleteUserAccountComponent;
    let fixture: ComponentFixture<DeleteUserAccountComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [DeleteUserAccountComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
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
