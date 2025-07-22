import {TestBed} from '@angular/core/testing';
import {ScopeService} from './scope.service';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('ScopeService', () => {
    let service: ScopeService;

    beforeEach(() => {
        TestBed.configureTestingModule({
    imports: [],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
});
        service = TestBed.inject(ScopeService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
