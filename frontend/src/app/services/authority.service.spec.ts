import {TestBed} from '@angular/core/testing';
import {AuthorityService} from './authority.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('AuthorityService', () => {
    let service: AuthorityService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule]
        });
        service = TestBed.inject(AuthorityService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
