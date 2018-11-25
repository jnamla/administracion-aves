import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReadAveComponent } from './read-ave.component';

describe('ReadAveComponent', () => {
  let component: ReadAveComponent;
  let fixture: ComponentFixture<ReadAveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReadAveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReadAveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
