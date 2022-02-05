import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateUserAccountComponent } from './create-user-account.component';

describe('CreateUserAccountComponent', () => {
  let component: CreateUserAccountComponent;
  let fixture: ComponentFixture<CreateUserAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateUserAccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateUserAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
