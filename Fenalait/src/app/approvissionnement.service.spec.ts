import { TestBed } from '@angular/core/testing';

import { ApprovissionnementService } from './approvissionnement.service';

describe('ApprovissionnementService', () => {
  let service: ApprovissionnementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApprovissionnementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
