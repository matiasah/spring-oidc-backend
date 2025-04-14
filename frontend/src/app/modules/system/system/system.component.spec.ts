import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SystemComponent } from './system.component';
import { TestLocalStorage } from "../../../util/test-local-storage.spec";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { MaterialModule } from "../../material/material.module";
import { NoopAnimationsModule } from "@angular/platform-browser/animations";
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from "@angular/platform-browser";
import { provideRouter } from '@angular/router';

describe('SystemComponent', () => {
    let component: SystemComponent;
    let fixture: ComponentFixture<SystemComponent>;
    let matIconRegistry: MatIconRegistry;
    let domSanitizer: DomSanitizer;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientTestingModule,
                NoopAnimationsModule,
                MaterialModule
            ],
            declarations: [SystemComponent],
            providers: [
                provideRouter([]),
                {
                    provide: Storage,
                    useValue: new TestLocalStorage()
                },
                {
                    provide: Window,
                    useValue: {}
                }
            ]
        }).compileComponents();

        matIconRegistry = TestBed.inject(MatIconRegistry);
        domSanitizer = TestBed.inject(DomSanitizer);

        // Add 'bot' icon
        matIconRegistry.addSvgIcon('bot', domSanitizer.bypassSecurityTrustResourceUrl('/assets/img/bot.svg'));
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(SystemComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
