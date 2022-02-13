import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteScopeComponent } from './delete-scope.component';

describe('DeleteScopeComponent', () => {
  let component: DeleteScopeComponent;
  let fixture: ComponentFixture<DeleteScopeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteScopeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteScopeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
