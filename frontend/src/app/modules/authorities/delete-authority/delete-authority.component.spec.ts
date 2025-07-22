import {ComponentFixture, TestBed} from '@angular/core/testing';
import {DeleteAuthorityComponent} from './delete-authority.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {MaterialModule} from "../../material/material.module";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {Authority} from "../../../interfaces/authority";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('DeleteAuthorityComponent', () => {
    let component: DeleteAuthorityComponent;
    let fixture: ComponentFixture<DeleteAuthorityComponent>;
    let authority: Authority = {} as any as Authority;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [DeleteAuthorityComponent],
    imports: [MaterialModule],
    providers: [
        {
            provide: MatDialogRef,
            useValue: new TestMatDialogRef()
        },
        {
            provide: MAT_DIALOG_DATA,
            useValue: authority
        },
        provideHttpClient(withInterceptorsFromDi()),
        provideHttpClientTesting()
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
