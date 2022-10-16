import {ComponentFixture, TestBed} from '@angular/core/testing';
import {CallbackComponent} from './callback.component';
import {RouterTestingModule} from "@angular/router/testing";
import {MatDialogRef} from "@angular/material/dialog";
import {TestMatDialogRef} from "../../../util/test-mat-dialog-ref.spec";
import {TestLocalStorage} from "../../../util/test-local-storage.spec";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MaterialModule} from "../../material/material.module";
import { LoginComponent } from '../login/login.component';

describe('CallbackComponent', () => {
    let component: CallbackComponent;
    let fixture: ComponentFixture<CallbackComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                RouterTestingModule.withRoutes([
                    {
                        path: 'login',
                        component: LoginComponent
                    }
                ]),
                HttpClientTestingModule,
                MaterialModule
            ],
            declarations: [
                CallbackComponent,
                LoginComponent
            ],
            providers: [
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
                }
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
