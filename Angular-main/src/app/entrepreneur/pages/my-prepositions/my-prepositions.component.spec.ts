import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyPrepositionsComponent } from './my-prepositions.component';

describe('MyPrepositionsComponent', () => {
  let component: MyPrepositionsComponent;
  let fixture: ComponentFixture<MyPrepositionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyPrepositionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyPrepositionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
