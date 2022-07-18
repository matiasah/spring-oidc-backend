import { TestBed } from '@angular/core/testing';

import { AuditEventService } from './audit-event.service';

describe('AuditEventService', () => {
  let service: AuditEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuditEventService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
