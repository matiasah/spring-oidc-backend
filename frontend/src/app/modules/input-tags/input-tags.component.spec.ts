import {ComponentFixture, TestBed} from '@angular/core/testing';
import {InputTagsComponent} from './input-tags.component';
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

describe('InputTagsComponent', () => {
    let component: InputTagsComponent;
    let fixture: ComponentFixture<InputTagsComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule,
                FormsModule,
                NoopAnimationsModule,
                MaterialModule
            ],
            declarations: [InputTagsComponent]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(InputTagsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
