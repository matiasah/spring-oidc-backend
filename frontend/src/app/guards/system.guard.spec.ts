import { TestBed } from '@angular/core/testing';

import { SystemGuard } from './system.guard';

describe('SystemGuard', () => {
  let guard: SystemGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(SystemGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
