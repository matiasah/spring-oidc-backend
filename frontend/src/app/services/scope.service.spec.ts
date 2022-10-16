import {TestBed} from '@angular/core/testing';
import {ScopeService} from './scope.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('ScopeService', () => {
    let service: ScopeService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ]
        });
        service = TestBed.inject(ScopeService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
