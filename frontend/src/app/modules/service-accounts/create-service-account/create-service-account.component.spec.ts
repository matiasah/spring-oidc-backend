import {ComponentFixture, TestBed} from '@angular/core/testing';
import {CreateServiceAccountComponent} from './create-service-account.component';
import {MatLegacyDialogRef as MatDialogRef} from "@angular/material/legacy-dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTagsModule} from "../../input-tags/input-tags.module";
import {InputAuthoritiesModule} from "../../input-authorities/input-authorities.module";
import {InputScopesTableModule} from "../../input-scopes-table/input-scopes-table.module";

describe('CreateServiceAccountComponent', () => {
    let component: CreateServiceAccountComponent;
    let fixture: ComponentFixture<CreateServiceAccountComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                ReactiveFormsModule,
                FormsModule,
                NoopAnimationsModule,
                MaterialModule,
                InputAuthoritiesModule,
                InputTagsModule,
                InputScopesTableModule
            ],
            declarations: [CreateServiceAccountComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
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
