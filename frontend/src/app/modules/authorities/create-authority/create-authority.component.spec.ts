import {ComponentFixture, TestBed, waitForAsync} from '@angular/core/testing';
import {CreateAuthorityComponent} from './create-authority.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTagsModule} from "../../input-tags/input-tags.module";

describe('CreateAuthorityComponent', () => {
    let component: CreateAuthorityComponent;
    let fixture: ComponentFixture<CreateAuthorityComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                ReactiveFormsModule,
                FormsModule,
                NoopAnimationsModule,
                MaterialModule,
                InputTagsModule
            ],
            declarations: [CreateAuthorityComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
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
