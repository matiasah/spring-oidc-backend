import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { MaterialModule } from "./modules/material/material.module";
import { provideHttpClientTesting } from "@angular/common/http/testing";
import { Router, RouterModule } from "@angular/router";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

describe('AppComponent', () => {

    let fixture: ComponentFixture<AppComponent>;
    let app: AppComponent;
    let router: Router;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [
                AppComponent
            ],
            imports: [RouterModule.forRoot([]), MaterialModule],
            providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
        }).compileComponents();
        router = TestBed.inject(Router);
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(AppComponent);
        app = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(app).toBeTruthy();
    });

    it('should navigate', async () => {

        const fixture = TestBed.createComponent(AppComponent);
        const app = fixture.componentInstance;
        expect(app).toBeTruthy();

        fixture.detectChanges()
        await fixture.whenStable()

        await router.navigate(['/']);

        fixture.detectChanges()
        await fixture.whenStable()
        await router.navigate(['/']);
        fixture.detectChanges()
        await fixture.whenStable()

    });

});
