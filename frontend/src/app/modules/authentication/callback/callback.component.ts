import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
    selector: 'app-callback',
    templateUrl: './callback.component.html',
    styleUrls: ['./callback.component.scss']
})
export class CallbackComponent implements OnInit {

    public constructor(
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private authService: AuthService,
        private snackBar: MatSnackBar
    ) { }

    public ngOnInit(): void {

        // Get activated route
        this.activatedRoute.queryParams.subscribe(
            queryParams => {

                // Get code
                const code = queryParams.code;

                // If there's a code
                if (code) {

                    // Exchange code for token
                    this.authService.exchangeCodeForToken(code).subscribe(
                        (accessToken) => {

                            // Set current access token
                            this.authService.setToken(accessToken);

                            // Redirect to system
                            this.router.navigate(['/sys']);
                        },
                        (error) => {

                            // Redirect to login
                            this.router.navigate(['/login']);

                            // Notify error
                            this.snackBar.open('Failed to authenticate, please try again later', 'Ok', { duration: 4000 });
                        }
                    );

                } else {

                    // Redirect to login
                    this.router.navigate(['/login']);
                }

            }
        );
        
    }

}
