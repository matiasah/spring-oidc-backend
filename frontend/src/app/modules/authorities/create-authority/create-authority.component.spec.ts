import {ComponentFixture, TestBed} from '@angular/core/testing';
import {CreateAuthorityComponent} from './create-authority.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";

describe('CreateAuthorityComponent', () => {
    let component: CreateAuthorityComponent;
    let fixture: ComponentFixture<CreateAuthorityComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [CreateAuthorityComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(CreateAuthorityComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
