import {TestBed} from '@angular/core/testing';
import {ServiceAccountService} from './service-account.service';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('ServiceAccountService', () => {
    let service: ServiceAccountService;

    beforeEach(() => {
        TestBed.configureTestingModule({
    imports: [],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
});
        service = TestBed.inject(ServiceAccountService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
