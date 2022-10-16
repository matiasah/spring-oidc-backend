import {ComponentFixture, TestBed} from '@angular/core/testing';
import {InputScopesTableComponent} from './input-scopes-table.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MaterialModule} from "../material/material.module";

describe('InputScopesTableComponent', () => {
    let component: InputScopesTableComponent;
    let fixture: ComponentFixture<InputScopesTableComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                MaterialModule
            ],
            declarations: [InputScopesTableComponent]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(InputScopesTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
