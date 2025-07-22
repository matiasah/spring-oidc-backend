import { TestBed } from '@angular/core/testing';
import { AuthorizationInfoService } from './authorization-info.service';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('AuthorizationInfoService', () => {
    let service: AuthorizationInfoService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [],
            providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
        });
        service = TestBed.inject(AuthorizationInfoService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

});
