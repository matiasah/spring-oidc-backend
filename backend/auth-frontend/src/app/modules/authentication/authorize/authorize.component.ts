import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorizationInfo } from 'src/app/interfaces/authorization-info';
import { AuthorizationInfoService } from 'src/app/services/authorization-info.service';
import { environment } from 'src/environments/environment';

@Component({
    selector: 'app-authorize',
    templateUrl: './authorize.component.html',
    styleUrls: ['./authorize.component.scss'],
    standalone: false
})
export class AuthorizeComponent implements OnInit {

    // Información sobre el cliente
    public authorizationInfo: AuthorizationInfo | undefined;
    public authorizeUrl: string = `${environment.host}/oauth2/authorize`;

    // Query parameters
    private scopes: string = '';
    private clientId: string = '';
    private state: string = '';

    public constructor(
        private authorizationInfoService: AuthorizationInfoService,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) { }

    public ngOnInit(): void {
        // Get query parameters
        this.activatedRoute.queryParams.subscribe(
            (queryParams) => {

                // Copy query parameters
                this.scopes = queryParams.scope;
                this.clientId = queryParams.client_id;
                this.state = queryParams.state;

                // If any of the parameters is missing
                if (!this.scopes || !this.clientId || !this.state) {

                    // Redirect to home
                    this.router.navigate(['/login']);
                    
                }

            }
        );
    }

    public ngAfterViewInit(): void {
        // Get authorization info
        this.authorizationInfoService.getAuthorizationInfo(this.scopes, this.clientId, this.state).subscribe(
            authorizationInfo => {

                // Copy authorizationInfo
                this.authorizationInfo = authorizationInfo;
            }
        );
    }

}
