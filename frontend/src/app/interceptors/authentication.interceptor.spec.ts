import { TestBed } from '@angular/core/testing';
import { AuthenticationInterceptor } from './authentication.interceptor';
import { TestLocalStorage } from "../util/test-local-storage.spec";
import { HttpTestingController, provideHttpClientTesting } from "@angular/common/http/testing";
import { HTTP_INTERCEPTORS, HttpClient, provideHttpClient, withInterceptorsFromDi } from "@angular/common/http";
import { AuthService } from "../services/auth.service";
import { firstValueFrom, of } from "rxjs";
import { environment } from "../../environments/environment";
import { RouterModule } from '@angular/router';

describe('AuthenticationInterceptor', () => {

    let authService: AuthService;
    let controller: HttpTestingController;
    let http: HttpClient;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [RouterModule.forRoot([])],
            providers: [
                AuthService,
                {
                    provide: HTTP_INTERCEPTORS,
                    useClass: AuthenticationInterceptor,
                    multi: true
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
        });
        authService = TestBed.inject(AuthService);
        controller = TestBed.inject(HttpTestingController);
        http = TestBed.inject(HttpClient);
    });

    it('should process token request normally', async () => {

        // Test get token
        firstValueFrom(authService.exchangeCodeForToken('test'));

        // Expect token request
        controller.expectOne(`${environment.host}/oauth2/token`).flush({});

        // Verify expectations
        controller.verify()

    });

    it('should inject a token', () => {

        // Mock isAuthenticated
        spyOn(authService, 'isAuthenticated').and.returnValue(of(true));

        // Mock getToken method
        spyOn(authService, 'getToken').and.returnValue({
            access_token: '',
            token_type: 'Bearer',
            refresh_token: '',
            expires_in: 0,
            scope: '',
            jti: ''
        });

        // Test request
        http.get(`${environment.host}/test`).subscribe();

        // Expect token request
        expect(controller.expectOne(`${environment.host}/test`).request.headers.get('Authorization')).toBeTruthy();

        // Verify expectations
        controller.verify()

    });

    it('should fail to inject a token', () => {

        // Mock isAuthenticated
        spyOn(authService, 'isAuthenticated').and.returnValue(of(true));

        // Mock getToken method
        spyOn(authService, 'getToken').and.returnValue(undefined);

        // Test request
        http.get(`${environment.host}/test`).subscribe();

        // Expect no token request
        expect(controller.expectOne(`${environment.host}/test`).request.headers.get('Authorization')).toBeFalsy();

        // Verify expectations
        controller.verify()

    });

    it('should not inject token if there\'s no authentication', () => {

        // Mock isAuthenticated
        spyOn(authService, 'isAuthenticated').and.returnValue(of(false));

        // Test request
        http.get(`${environment.host}/test`).subscribe();

        // Expect no token request
        expect(controller.expectOne(`${environment.host}/test`).request.headers.get('Authorization')).toBeFalsy();

        // Verify expectations
        controller.verify()

    });

    it('should not inject token if it\s consuming a different api', () => {

        // Mock isAuthenticated
        spyOn(authService, 'isAuthenticated').and.returnValue(of(true));

        // Test request
        http.get(`/test`).subscribe();

        // Expect no token request
        controller.expectNone(`${environment.host}/test`);
        expect(controller.expectOne(`/test`).request.headers.get('Authorization')).toBeFalsy();

        // Verify expectations
        controller.verify()

    });

});
