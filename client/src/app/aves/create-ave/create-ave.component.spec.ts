import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAveComponent } from './create-ave.component';

describe('CreateAveComponent', () => {
  let component: CreateAveComponent;
  let fixture: ComponentFixture<CreateAveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateAveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateAveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
