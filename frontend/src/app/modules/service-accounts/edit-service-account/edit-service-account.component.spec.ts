import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditServiceAccountComponent } from './edit-service-account.component';

describe('EditServiceAccountComponent', () => {
  let component: EditServiceAccountComponent;
  let fixture: ComponentFixture<EditServiceAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditServiceAccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditServiceAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
