import {ComponentFixture, TestBed} from '@angular/core/testing';
import {UserAccountsComponent} from './user-accounts.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "../../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

describe('UserAccountsComponent', () => {
    let component: UserAccountsComponent;
    let fixture: ComponentFixture<UserAccountsComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                ReactiveFormsModule,
                FormsModule,
                NoopAnimationsModule,
                MaterialModule
            ],
            declarations: [UserAccountsComponent]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(UserAccountsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
