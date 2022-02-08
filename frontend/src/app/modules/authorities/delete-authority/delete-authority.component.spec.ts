import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteAuthorityComponent } from './delete-authority.component';

describe('DeleteAuthorityComponent', () => {
  let component: DeleteAuthorityComponent;
  let fixture: ComponentFixture<DeleteAuthorityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteAuthorityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteAuthorityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
