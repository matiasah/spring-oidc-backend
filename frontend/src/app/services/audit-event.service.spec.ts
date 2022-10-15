import {TestBed} from '@angular/core/testing';
import {AuditEventService} from './audit-event.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('AuditEventService', () => {
    let service: AuditEventService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
            ]
        });
        service = TestBed.inject(AuditEventService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
