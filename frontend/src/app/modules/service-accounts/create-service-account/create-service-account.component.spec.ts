import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateServiceAccountComponent } from './create-service-account.component';

describe('CreateServiceAccountComponent', () => {
  let component: CreateServiceAccountComponent;
  let fixture: ComponentFixture<CreateServiceAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateServiceAccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateServiceAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
