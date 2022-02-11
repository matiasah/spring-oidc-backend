import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';

/**
 * This guard is used to prevent authenticated users from accessing the login page.
 * 
 * @author Matías Hermosilla
 * @since 30-01-2022
 */
@Injectable({
    providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

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

                // If the user is authenticated, redirect to the system module
                if (isAuthenticated) {

                    // Redirect to system module
                    this.router.navigate(['/sys']);

                    // Return false to prevent access to the login page
                    return false;
                }

                // Allow access to view if user is not authenticated
                return true;
            }
        ));
    }

}
