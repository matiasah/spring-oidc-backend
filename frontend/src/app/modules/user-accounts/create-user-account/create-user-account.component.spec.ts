import {ComponentFixture, TestBed} from '@angular/core/testing';
import {CreateUserAccountComponent} from './create-user-account.component';
import {MaterialModule} from "../../material/material.module";
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";

describe('CreateUserAccountComponent', () => {
    let component: CreateUserAccountComponent;
    let fixture: ComponentFixture<CreateUserAccountComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                MaterialModule
            ],
            declarations: [CreateUserAccountComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(CreateUserAccountComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
