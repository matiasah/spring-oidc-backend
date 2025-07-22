import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CallbackComponent } from './callback.component';
import { MatDialogRef } from "@angular/material/dialog";
import { TestMatDialogRef } from "../../../util/test-mat-dialog-ref.spec";
import { TestLocalStorage } from "../../../util/test-local-storage.spec";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { MaterialModule } from "../../material/material.module";
import { LoginComponent } from '../login/login.component';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('CallbackComponent', () => {
    let component: CallbackComponent;
    let fixture: ComponentFixture<CallbackComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
    declarations: [
        CallbackComponent,
        LoginComponent
    ],
    imports: [MaterialModule],
    providers: [
        provideRouter([
            {
                path: 'login',
                component: LoginComponent
            }
        ]),
        {
            provide: MatDialogRef,
            useValue: new TestMatDialogRef()
        },
        {
            provide: Storage,
            useValue: new TestLocalStorage()
        },
        {
            provide: Window,
            useValue: {}
        },
        provideHttpClient(withInterceptorsFromDi()),
        provideHttpClientTesting()
    ]
}).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(CallbackComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
