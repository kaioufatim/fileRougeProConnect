import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupCreateurComponent } from './signup-createur.component';

describe('SignupCreateurComponent', () => {
  let component: SignupCreateurComponent;
  let fixture: ComponentFixture<SignupCreateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignupCreateurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SignupCreateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
