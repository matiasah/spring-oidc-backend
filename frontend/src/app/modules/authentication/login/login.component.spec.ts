import {ComponentFixture, TestBed} from '@angular/core/testing';
import {LoginComponent} from './login.component';
import {TestLocalStorage} from "../../../util/test-local-storage.spec";
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('LoginComponent', () => {
    let component: LoginComponent;
    let fixture: ComponentFixture<LoginComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                HttpClientTestingModule
            ],
            declarations: [LoginComponent],
            providers: [
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
        fixture = TestBed.createComponent(LoginComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
