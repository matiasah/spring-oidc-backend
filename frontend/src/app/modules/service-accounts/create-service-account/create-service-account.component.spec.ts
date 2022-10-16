import {ComponentFixture, TestBed} from '@angular/core/testing';
import {CreateServiceAccountComponent} from './create-service-account.component';
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";

describe('CreateServiceAccountComponent', () => {
    let component: CreateServiceAccountComponent;
    let fixture: ComponentFixture<CreateServiceAccountComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                NoopAnimationsModule,
                MaterialModule
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
