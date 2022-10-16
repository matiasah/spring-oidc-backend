import {TestBed} from '@angular/core/testing';
import {AuthService} from './auth.service';
import {TestLocalStorage} from "../util/test-local-storage.spec";
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('AuthService', () => {
    let service: AuthService;

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
        service = TestBed.inject(AuthService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
