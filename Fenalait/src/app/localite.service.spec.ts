import { TestBed } from '@angular/core/testing';

import { LocaliteService } from './localite.service';

describe('LocaliteService', () => {
  let service: LocaliteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocaliteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
