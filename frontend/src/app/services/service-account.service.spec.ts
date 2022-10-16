import {TestBed} from '@angular/core/testing';
import {ServiceAccountService} from './service-account.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('ServiceAccountService', () => {
    let service: ServiceAccountService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ]
        });
        service = TestBed.inject(ServiceAccountService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
