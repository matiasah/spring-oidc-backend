import {ComponentFixture, TestBed} from '@angular/core/testing';
import {DeleteScopeComponent} from './delete-scope.component';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {MaterialModule} from "../../material/material.module";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {Scope} from "../../../interfaces/scope";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('DeleteScopeComponent', () => {
    let component: DeleteScopeComponent;
    let fixture: ComponentFixture<DeleteScopeComponent>;
    let scope: Scope = {} as any as Scope;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [DeleteScopeComponent],
    imports: [NoopAnimationsModule,
        MaterialModule],
    providers: [
        {
            provide: MatDialogRef,
            useValue: new TestMatDialogRef()
        },
        {
            provide: MAT_DIALOG_DATA,
            useValue: scope
        },
        provideHttpClient(withInterceptorsFromDi()),
        provideHttpClientTesting()
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
