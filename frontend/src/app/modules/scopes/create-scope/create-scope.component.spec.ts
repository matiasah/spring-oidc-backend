import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateScopeComponent} from './create-scope.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";

describe('CreateScopeComponent', () => {
    let component: CreateScopeComponent;
    let fixture: ComponentFixture<CreateScopeComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [CreateScopeComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(CreateScopeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
