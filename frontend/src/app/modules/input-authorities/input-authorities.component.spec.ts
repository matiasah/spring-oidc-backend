import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputAuthoritiesComponent } from './input-authorities.component';

describe('InputAuthoritiesComponent', () => {
  let component: InputAuthoritiesComponent;
  let fixture: ComponentFixture<InputAuthoritiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InputAuthoritiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InputAuthoritiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
