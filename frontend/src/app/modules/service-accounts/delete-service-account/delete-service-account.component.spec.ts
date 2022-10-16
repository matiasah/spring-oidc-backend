import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DeleteServiceAccountComponent} from './delete-service-account.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MaterialModule} from "../../material/material.module";

describe('DeleteServiceAccountComponent', () => {
    let component: DeleteServiceAccountComponent;
    let fixture: ComponentFixture<DeleteServiceAccountComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                MaterialModule
            ],
            declarations: [DeleteServiceAccountComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(DeleteServiceAccountComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
