import {TestBed} from '@angular/core/testing';
import {SystemGuard} from './system.guard';
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {TestLocalStorage} from "../util/test-local-storage.spec";
import {of} from "rxjs";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {Component} from "@angular/core";

@Component({
    template: ``
})
class TestComponent {
}

describe('SystemGuard', () => {
    let guard: SystemGuard;
    let authService: AuthService;
    let router: Router;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule.withRoutes([
                    {
                        path: 'sys',
                        component: TestComponent,
                        canActivate: [SystemGuard]
                    },
                    {
                        path: '',
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
        guard = TestBed.inject(SystemGuard);
        authService = TestBed.inject(AuthService);
        router = TestBed.inject(Router);
    });

    it('should be created', () => {
        expect(guard).toBeTruthy();
    });

    it('should allow access', async () => {

        // Spy isAuthenticated
        spyOn(authService, 'isAuthenticated').and.returnValue(of(true));

        // Route to root
        await router.navigate(['/sys']);

        // Expect route to be "/"
        expect(router.url).toBe('/sys');

    });

    it('should not allow access', async () => {

        // Spy isAuthenticated
        spyOn(authService, 'isAuthenticated').and.returnValue(of(false));

        // Route to root
        await router.navigate(['/sys']);

        // Expect route to be "/sys"
        expect(router.url).toBe('/');

    });

});
