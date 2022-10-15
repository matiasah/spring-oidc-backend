import {ComponentFixture, TestBed} from '@angular/core/testing';
import {AuthoritiesComponent} from './authorities.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('AuthoritiesComponent', () => {
    let component: AuthoritiesComponent;
    let fixture: ComponentFixture<AuthoritiesComponent>;

    beforeEach(async (compileComponents) => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule
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
