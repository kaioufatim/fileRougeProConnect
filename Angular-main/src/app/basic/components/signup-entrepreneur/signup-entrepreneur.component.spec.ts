import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupEntrepreneurComponent } from './signup-entrepreneur.component';

describe('SignupEntrepreneurComponent', () => {
  let component: SignupEntrepreneurComponent;
  let fixture: ComponentFixture<SignupEntrepreneurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignupEntrepreneurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SignupEntrepreneurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
