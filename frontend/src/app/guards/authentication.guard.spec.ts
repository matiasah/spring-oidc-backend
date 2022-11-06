import {TestBed} from '@angular/core/testing';
import {AuthenticationGuard} from './authentication.guard';
import {TestLocalStorage} from "../util/test-local-storage.spec";
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {AuthService} from "../services/auth.service";
import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {of} from "rxjs";

@Component({
    template: ``
})
class TestComponent {
}

describe('AuthenticationGuard', () => {
    let guard: AuthenticationGuard;
    let authService: AuthService;
    let router: Router;

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [
                TestComponent
            ],
            imports: [
                RouterTestingModule.withRoutes([
                    {
                        path: '',
                        component: TestComponent,
                        canActivate: [AuthenticationGuard]
                    },
                    {
                        path: 'sys',
                        component: TestComponent
                    }
                ]),
                HttpClientTestingModule
            ],
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
        });
        guard = TestBed.inject(AuthenticationGuard);
        authService = TestBed.inject(AuthService);
        router = TestBed.inject(Router);
    });

    it('should be created', () => {
        expect(guard).toBeTruthy();
    });

    it('should allow access', async () => {

        // Spy isAuthenticated
        spyOn(authService, 'isAuthenticated').and.returnValue(of(false));

        // Route to root
        await router.navigate(['/']);

        // Expect route to be "/"
        expect(router.url).toBe('/');

    });

    it('should not allow access', async () => {

        // Spy isAuthenticated
        spyOn(authService, 'isAuthenticated').and.returnValue(of(true));

        // Route to root
        await router.navigate(['/']);

        // Expect route to be "/sys"
        expect(router.url).toBe('/sys');

    });

});
