import {TestBed} from '@angular/core/testing';
import {AuthenticationGuard} from './authentication.guard';
import {TestLocalStorage} from "../util/test-local-storage.spec";
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('AuthenticationGuard', () => {
    let guard: AuthenticationGuard;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
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
    });

    it('should be created', () => {
        expect(guard).toBeTruthy();
    });

});
