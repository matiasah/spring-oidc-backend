import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAuthorityComponent } from './create-authority.component';

describe('CreateAuthorityComponent', () => {
  let component: CreateAuthorityComponent;
  let fixture: ComponentFixture<CreateAuthorityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateAuthorityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateAuthorityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
