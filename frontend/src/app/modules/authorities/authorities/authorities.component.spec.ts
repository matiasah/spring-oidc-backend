import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthoritiesComponent } from './authorities.component';

describe('AuthoritiesComponent', () => {
  let component: AuthoritiesComponent;
  let fixture: ComponentFixture<AuthoritiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthoritiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthoritiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
