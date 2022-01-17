import { Component } from '@angular/core';
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
        private router: Router
    ) {

        // Subscribe to router events
        this.router.events.subscribe(
            (event: Event) => {

                // Event received
                if (event instanceof NavigationStart) {
                    if (!this.loading) {
                        // Start loading
                        this.loading = true;
                    }
                } else if (event instanceof NavigationEnd || event instanceof NavigationCancel || event instanceof NavigationError) {
                    if (this.loading) {
                        // End loading
                        this.loading = false;
                    }
                }
            }
        );

    }

}
