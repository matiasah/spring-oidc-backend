import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AuthorizeComponent } from './authorize.component';
import { HttpTestingController, provideHttpClientTesting } from "@angular/common/http/testing";
import { LoginComponent } from '../login/login.component';
import { NoopAnimationsModule } from "@angular/platform-browser/animations";
import { MaterialModule } from "../../material/material.module";
import { environment } from "../../../../environments/environment";
import { AuthorizationInfo } from "../../../interfaces/authorization-info";
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { RouterModule } from '@angular/router';

describe('AuthorizeComponent', () => {
    let component: AuthorizeComponent;
    let fixture: ComponentFixture<AuthorizeComponent>;
    let controller: HttpTestingController;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [
                AuthorizeComponent,
                LoginComponent
            ],
            imports: [RouterModule.forRoot([
                {
                    path: 'login',
                    component: LoginComponent
                }
            ]),
                NoopAnimationsModule,
                MaterialModule],
            providers: [provideHttpClient(withInterceptorsFromDi()), provideHttpClientTesting()]
        }).compileComponents();
        controller = TestBed.inject(HttpTestingController);
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(AuthorizeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should get authorization info', () => {
        expect(component).toBeTruthy();

        let authorizationInfo: AuthorizationInfo = {
            principal: {
                authenticated: true,
                authorities: [],
                principal: {
                    id: '',
                    username: '',
                    authorities: [],
                    accountNonExpired: true,
                    accountNonLocked: true,
                    credentialsNonExpired: true,
                    enabled: true,
                    updatedAt: '',
                    createdAt: ''
                }
            },
            client: {
                id: '',
                clientId: '',
                clientIdIssuedAt: 0,
                clientSecret: '',
                clientSecretExpiresAt: 0,
                clientName: '',
                clientAuthenticationMethods: [],
                authorizationGrantTypes: [],
                redirectUris: [],
                scopes: [],
                clientSettings: {},
                tokenSettings: {}
            },
            scopes: [],
            state: ''
        }

        // Test request
        controller.expectOne(`${environment.host}/oauth2/authorization-info?scope=undefined&client_id=undefined&state=undefined`).flush(authorizationInfo);
    });

});
