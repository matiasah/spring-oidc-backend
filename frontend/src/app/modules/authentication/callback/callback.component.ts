import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
    selector: 'app-callback',
    templateUrl: './callback.component.html',
    styleUrls: ['./callback.component.scss']
})
export class CallbackComponent implements OnInit {

    public constructor(
        private activatedRoute: ActivatedRoute,
        private authService: AuthService
    ) { }

    public ngOnInit(): void {

        // Get activated route
        this.activatedRoute.queryParams.subscribe(
            queryParams => {

                // Get code
                const code = queryParams.code;

                // Exchange code for token
                this.authService.exchangeCodeForToken(code).subscribe(
                    (accessToken) => {

                        // Set current access token
                        this.authService.setToken(accessToken);
                    }
                );

            }
        );
        
    }

}
