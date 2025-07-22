import {TestBed} from '@angular/core/testing';
import {AuthorityService} from './authority.service';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('AuthorityService', () => {
    let service: AuthorityService;

    beforeEach(() => {
        TestBed.configureTestingModule({
    imports: [],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
});
        service = TestBed.inject(AuthorityService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
