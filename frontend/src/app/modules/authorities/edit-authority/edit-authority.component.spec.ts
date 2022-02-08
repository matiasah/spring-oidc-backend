import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAuthorityComponent } from './edit-authority.component';

describe('EditAuthorityComponent', () => {
  let component: EditAuthorityComponent;
  let fixture: ComponentFixture<EditAuthorityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditAuthorityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditAuthorityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
