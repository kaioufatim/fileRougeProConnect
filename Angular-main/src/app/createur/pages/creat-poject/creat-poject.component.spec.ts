import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatPojectComponent } from './creat-poject.component';

describe('CreatPojectComponent', () => {
  let component: CreatPojectComponent;
  let fixture: ComponentFixture<CreatPojectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatPojectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatPojectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
