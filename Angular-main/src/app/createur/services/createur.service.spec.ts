import { TestBed } from '@angular/core/testing';

import { CreateurService } from './createur.service';

describe('CreateurService', () => {
  let service: CreateurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
