import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteServiceAccountComponent } from './delete-service-account.component';

describe('DeleteServiceAccountComponent', () => {
  let component: DeleteServiceAccountComponent;
  let fixture: ComponentFixture<DeleteServiceAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteServiceAccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteServiceAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
