import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    standalone: false
})
export class LoginComponent implements OnInit {

    // Indicates if there was an error during login process
    public loginError: boolean = false;

    // User
    public username: string = '';
    public password: string = '';

    // Host
    public host: string = environment.host;

    public constructor(
        private activatedRoute: ActivatedRoute
    ) { }

    public ngOnInit(): void {

        // Detect error
        this.activatedRoute.queryParams.subscribe(
            queryParams => {

                // Toggle error flag
                this.loginError = queryParams.error;

            }
        );

    }

}
