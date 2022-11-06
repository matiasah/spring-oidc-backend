import { Component } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { Event, NavigationCancel, NavigationEnd, NavigationError, NavigationStart, Router } from '@angular/router';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {

    // Is the page loading?
    public loading = true;

    public constructor(
        private router: Router,
        private iconRegistry: MatIconRegistry,
        private domSanitizer: DomSanitizer
    ) {

        // Subscribe to router events
        this.router.events.subscribe(
            (event: Event) => {

                // Event received
                if (event instanceof NavigationStart) {

                    // Start loading
                    this.loading = true;

                } else if (event instanceof NavigationEnd || event instanceof NavigationCancel || event instanceof NavigationError) {

                    // End loading
                    this.loading = false;

                }
            }
        );

        // Add 'bot' icon
        this.iconRegistry.addSvgIcon('bot', this.domSanitizer.bypassSecurityTrustResourceUrl('/assets/img/bot.svg'));

    }

}
