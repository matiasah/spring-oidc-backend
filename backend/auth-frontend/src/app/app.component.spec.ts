import {TestBed} from '@angular/core/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {AppComponent} from './app.component';
import {Router} from "@angular/router";
import {MaterialModule} from "./modules/material/material.module";

describe('AppComponent', () => {

  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        MaterialModule
      ],
      declarations: [
        AppComponent
      ],
    }).compileComponents();
    router = TestBed.inject(Router)
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
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

  });

});
