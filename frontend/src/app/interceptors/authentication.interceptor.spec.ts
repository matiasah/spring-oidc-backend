import {TestBed} from '@angular/core/testing';
import {AuthenticationInterceptor} from './authentication.interceptor';
import {TestLocalStorage} from "../util/test-local-storage.spec";
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthService} from "../services/auth.service";
import {firstValueFrom} from "rxjs";
import {environment} from "../../environments/environment";

describe('AuthenticationInterceptor', () => {

    let authService: AuthService;
    let controller: HttpTestingController;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                HttpClientTestingModule
            ],
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
                }
            ]
        });
        authService = TestBed.inject(AuthService);
        controller = TestBed.inject(HttpTestingController);
    });

    it('should process token request normally', async () => {

        // Test get token
        firstValueFrom(authService.exchangeCodeForToken('test'));

        // Expect token request
        controller.expectOne(`${environment.host}/oauth2/token`).flush({});

        // Verify expectations
        controller.verify()

    });

});
