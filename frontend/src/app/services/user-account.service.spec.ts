import {TestBed} from '@angular/core/testing';
import {UserAccountService} from './user-account.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('UserAccountService', () => {
    let service: UserAccountService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ]
        });
        service = TestBed.inject(UserAccountService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
