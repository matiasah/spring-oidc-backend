import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuditEventsComponent } from './audit-events.component';

describe('AuditEventsComponent', () => {
  let component: AuditEventsComponent;
  let fixture: ComponentFixture<AuditEventsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuditEventsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuditEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
