import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    // User that will log in
    public user: User = {} as User;

    // Form
    @ViewChild('form')
    public form!: NgForm;

    // Indicate if a login request is being made
    public requesting: boolean = false;

    // Indicate if there was an error during the request
    public incorrect: boolean = false;

    public constructor(
        private authService: AuthService,
        private router: Router
    ) {

    }

    public ngOnInit(): void {}

    public onSubmit(): void {
        // If the form is valid
        if (this.form.valid) {

            // Indicate that a request is being made
            this.requesting = true;

            // Indicate that there has not been an error yet
            this.incorrect = false;

            // Login
            this.authService.login(this.user).subscribe(
                () => {
                    // Indicate that the request has finished
                    this.requesting = false;

                    // Redirect to home
                    this.router.navigate(['system']);
                },
                () => {
                    // Indicate that the request has finished
                    this.requesting = false;

                    // Indicate that there was an error
                    this.incorrect = true;
                }
            );
        }
    }

}
