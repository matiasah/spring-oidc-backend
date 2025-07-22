import {ComponentFixture, TestBed} from '@angular/core/testing';
import {CreateUserAccountComponent} from './create-user-account.component';
import {MaterialModule} from "../../material/material.module";
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTagsModule} from "../../input-tags/input-tags.module";
import {InputAuthoritiesModule} from "../../input-authorities/input-authorities.module";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('CreateUserAccountComponent', () => {
    let component: CreateUserAccountComponent;
    let fixture: ComponentFixture<CreateUserAccountComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [CreateUserAccountComponent],
    imports: [ReactiveFormsModule,
        FormsModule,
        NoopAnimationsModule,
        MaterialModule,
        InputAuthoritiesModule,
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
        fixture = TestBed.createComponent(CreateUserAccountComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
