import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';

/**
 * This guard is used to prevent unauthenticated users from accessing the system module.
 * 
 * @author Matías Hermosilla
 * @since 30-01-2022
 */
@Injectable({
    providedIn: 'root'
})
export class SystemGuard  {

    public constructor(
        private authService: AuthService,
        private router: Router
    ) {

    }

    public canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

        // Return isAuthenticated observable
        return this.authService.isAuthenticated().pipe(map(
            (isAuthenticated) => {

                // If the user is authenticated
                if (isAuthenticated) {

                    // Allow access to view
                    return true;

                }

                // Redirect to login page
                this.router.navigate(['/']);

                // Return false to prevent access to the system module
                return false;
            }
        ));
    }

}
