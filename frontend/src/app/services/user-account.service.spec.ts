import {TestBed} from '@angular/core/testing';
import {UserAccountService} from './user-account.service';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('UserAccountService', () => {
    let service: UserAccountService;

    beforeEach(() => {
        TestBed.configureTestingModule({
    imports: [],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
});
        service = TestBed.inject(UserAccountService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
