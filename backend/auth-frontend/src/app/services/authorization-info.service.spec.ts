import {TestBed} from '@angular/core/testing';
import {AuthorizationInfoService} from './authorization-info.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('AuthorizationInfoService', () => {
  let service: AuthorizationInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ]
    });
    service = TestBed.inject(AuthorizationInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

});
