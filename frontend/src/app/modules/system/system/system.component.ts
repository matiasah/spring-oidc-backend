import { Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { RouterService } from 'src/app/services/router.service';

@Component({
    selector: 'app-system',
    templateUrl: './system.component.html',
    styleUrls: ['./system.component.scss']
})
export class SystemComponent implements OnInit {

    // Suscripción a router
    private subscription!: Subscription;

    public constructor(
        private authService: AuthService,
        private routerService: RouterService,
        private router: Router
    ) {

    }

    public ngOnInit(): void {
        // Suscribirse a eventos de router
        this.subscription = this.router.events.subscribe(
            event => {
                // Evento recibido
                if (event instanceof NavigationStart) {
                    // Eliminar URL
                    this.routerService.backUrl.next(undefined);
                }
            }
        );
    }

    public ngOnDestroy(): void {
        // Si hay suscripción a router
        if (this.subscription) {
            // Eliminar suscripción
            this.subscription.unsubscribe();
        }
    }

    public onLogout(): void {
        // Eliminar token
        this.authService.logout();
    }

    public get backUrl(): Observable<undefined | string> {
        return this.routerService.backUrl;
    }

}
