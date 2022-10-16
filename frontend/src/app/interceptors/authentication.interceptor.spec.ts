import {TestBed} from '@angular/core/testing';
import {AuthenticationInterceptor} from './authentication.interceptor';
import {TestLocalStorage} from "../util/test-local-storage.spec";
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('AuthenticationInterceptor', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            RouterTestingModule,
            HttpClientTestingModule
        ],
        providers: [
            AuthenticationInterceptor,
            {
                provide: Storage,
                useValue: new TestLocalStorage()
            },
            {
                provide: Window,
                useValue: {}
            }
        ]
    }));

    it('should be created', () => {
        const interceptor: AuthenticationInterceptor = TestBed.inject(AuthenticationInterceptor);
        expect(interceptor).toBeTruthy();
    });
});
