import {ComponentFixture, TestBed} from '@angular/core/testing';
import {CreateAuthorityComponent} from './create-authority.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTagsModule} from "../../input-tags/input-tags.module";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('CreateAuthorityComponent', () => {
    let component: CreateAuthorityComponent;
    let fixture: ComponentFixture<CreateAuthorityComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [CreateAuthorityComponent],
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
        fixture = TestBed.createComponent(CreateAuthorityComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
