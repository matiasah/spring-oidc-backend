import { TestBed } from '@angular/core/testing';

import { ServiceAccountService } from './service-account.service';

describe('ServiceAccountService', () => {
  let service: ServiceAccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceAccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
