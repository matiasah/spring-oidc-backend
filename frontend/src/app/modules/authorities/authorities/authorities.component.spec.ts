import {ComponentFixture, TestBed, waitForAsync} from '@angular/core/testing';
import {AuthoritiesComponent} from './authorities.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MaterialModule} from "../../material/material.module";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";

describe('AuthoritiesComponent', () => {
    let component: AuthoritiesComponent;
    let fixture: ComponentFixture<AuthoritiesComponent>;

    beforeEach(async  () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                NoopAnimationsModule,
                MaterialModule
            ],
            declarations: [AuthoritiesComponent]
        }).compileComponents();
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
