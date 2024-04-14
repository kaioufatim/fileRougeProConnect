import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateurComponent } from './createur.component';

describe('CreateurComponent', () => {
  let component: CreateurComponent;
  let fixture: ComponentFixture<CreateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
