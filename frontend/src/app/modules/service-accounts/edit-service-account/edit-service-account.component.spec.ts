import {ComponentFixture, TestBed} from '@angular/core/testing';
import {EditServiceAccountComponent} from './edit-service-account.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";

describe('EditServiceAccountComponent', () => {
    let component: EditServiceAccountComponent;
    let fixture: ComponentFixture<EditServiceAccountComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [EditServiceAccountComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
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
