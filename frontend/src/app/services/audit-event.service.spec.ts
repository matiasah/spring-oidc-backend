import {TestBed} from '@angular/core/testing';
import {AuditEventService} from './audit-event.service';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('AuditEventService', () => {
    let service: AuditEventService;

    beforeEach(() => {
        TestBed.configureTestingModule({
    imports: [],
    providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
});
        service = TestBed.inject(AuditEventService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
