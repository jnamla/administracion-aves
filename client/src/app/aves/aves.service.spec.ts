import { TestBed } from '@angular/core/testing';

import { AvesService } from './aves.service';

describe('AvesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AvesService = TestBed.get(AvesService);
    expect(service).toBeTruthy();
  });
});
