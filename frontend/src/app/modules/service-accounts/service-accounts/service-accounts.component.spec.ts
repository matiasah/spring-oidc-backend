import {ComponentFixture, TestBed} from '@angular/core/testing';
import {ServiceAccountsComponent} from './service-accounts.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";

describe('ServiceAccountsComponent', () => {
    let component: ServiceAccountsComponent;
    let fixture: ComponentFixture<ServiceAccountsComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ],
            declarations: [ServiceAccountsComponent],
            providers: [
                {
                    provide: MatDialogRef,
                    useValue: new TestMatDialogRef()
                }
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ServiceAccountsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
