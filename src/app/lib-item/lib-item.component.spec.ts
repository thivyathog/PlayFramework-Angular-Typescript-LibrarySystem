import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LibItemComponent } from './lib-item.component';

describe('LibItemComponent', () => {
  let component: LibItemComponent;
  let fixture: ComponentFixture<LibItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LibItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LibItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
