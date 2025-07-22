import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateScopeComponent} from './create-scope.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTagsModule} from "../../input-tags/input-tags.module";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('CreateScopeComponent', () => {
    let component: CreateScopeComponent;
    let fixture: ComponentFixture<CreateScopeComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [CreateScopeComponent],
    imports: [ReactiveFormsModule,
        FormsModule,
        NoopAnimationsModule,
        MaterialModule,
        InputTagsModule],
    providers: [
        {
            provide: MatDialogRef,
            useValue: new TestMatDialogRef()
        },
        provideHttpClient(withInterceptorsFromDi()),
        provideHttpClientTesting()
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
