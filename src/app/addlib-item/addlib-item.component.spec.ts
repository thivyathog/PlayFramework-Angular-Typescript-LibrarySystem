import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddlibItemComponent } from './addlib-item.component';

describe('AddlibItemComponent', () => {
  let component: AddlibItemComponent;
  let fixture: ComponentFixture<AddlibItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddlibItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddlibItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
