import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AuditEventsComponent } from './audit-events.component';
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { MaterialModule } from "../../material/material.module";
import { NoopAnimationsModule } from "@angular/platform-browser/animations";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('AuditEventsComponent', () => {
    let component: AuditEventsComponent;
    let fixture: ComponentFixture<AuditEventsComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [AuditEventsComponent],
            imports: [NoopAnimationsModule, MaterialModule],
            providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
        }).compileComponents();

        fixture = TestBed.createComponent(AuditEventsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
