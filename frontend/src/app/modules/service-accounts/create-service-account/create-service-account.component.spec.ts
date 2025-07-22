import {ComponentFixture, TestBed} from '@angular/core/testing';
import {CreateServiceAccountComponent} from './create-service-account.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTagsModule} from "../../input-tags/input-tags.module";
import {InputAuthoritiesModule} from "../../input-authorities/input-authorities.module";
import {InputScopesTableModule} from "../../input-scopes-table/input-scopes-table.module";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('CreateServiceAccountComponent', () => {
    let component: CreateServiceAccountComponent;
    let fixture: ComponentFixture<CreateServiceAccountComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [CreateServiceAccountComponent],
    imports: [ReactiveFormsModule,
        FormsModule,
        NoopAnimationsModule,
        MaterialModule,
        InputAuthoritiesModule,
        InputTagsModule,
        InputScopesTableModule],
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
        fixture = TestBed.createComponent(CreateServiceAccountComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
