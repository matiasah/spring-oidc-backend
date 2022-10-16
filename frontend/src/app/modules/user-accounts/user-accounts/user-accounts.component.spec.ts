import {ComponentFixture, TestBed} from '@angular/core/testing';
import {UserAccountsComponent} from './user-accounts.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('UserAccountsComponent', () => {
    let component: UserAccountsComponent;
    let fixture: ComponentFixture<UserAccountsComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
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
