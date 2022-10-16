import {ComponentFixture, TestBed} from '@angular/core/testing';
import {ScopesComponent} from './scopes.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";

describe('ScopesComponent', () => {
    let component: ScopesComponent;
    let fixture: ComponentFixture<ScopesComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                NoopAnimationsModule,
                MaterialModule
            ],
            declarations: [ScopesComponent]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ScopesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
