import { TestBed } from '@angular/core/testing';

import { AuthorizationInfoService } from './authorization-info.service';

describe('AuthorizationInfoService', () => {
  let service: AuthorizationInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthorizationInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
