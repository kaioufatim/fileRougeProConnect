import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateurdashboardComponent } from './createurdashboard.component';

describe('CreateurdashboardComponent', () => {
  let component: CreateurdashboardComponent;
  let fixture: ComponentFixture<CreateurdashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateurdashboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateurdashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
