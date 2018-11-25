import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchZonaAveComponent } from './search-zona-ave.component';

describe('SearchZonaAveComponent', () => {
  let component: SearchZonaAveComponent;
  let fixture: ComponentFixture<SearchZonaAveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchZonaAveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchZonaAveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
