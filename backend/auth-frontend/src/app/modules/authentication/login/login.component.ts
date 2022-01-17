import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    // Indicar si el usuario o contraseña son incorrectos
    public incorrecto: boolean = false;

    // Usuario
    public username: string = '';
    public password: string = '';

    // Host
    public host: string = environment.host;

    public constructor(
        private activatedRoute: ActivatedRoute
    ) { }

    public ngOnInit(): void {
        // Identificar error
        this.activatedRoute.queryParams.subscribe(
            queryParams => {
                // Identificar error
                this.incorrecto = queryParams.error;
            }
        );
    }

}
