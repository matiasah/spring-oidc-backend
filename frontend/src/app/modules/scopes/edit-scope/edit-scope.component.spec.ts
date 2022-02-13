import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditScopeComponent } from './edit-scope.component';

describe('EditScopeComponent', () => {
  let component: EditScopeComponent;
  let fixture: ComponentFixture<EditScopeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditScopeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditScopeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
