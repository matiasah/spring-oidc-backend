import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
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
export class SystemGuard implements CanActivate {

    public constructor(
        private authService: AuthService,
        private router: Router
    ) {

    }

    public canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

        // If the user is authenticated
        if (this.authService.isAuthenticated()) {

            // Allow access to view
            return true;

        }
        
        // Redirect to login page
        this.router.navigate(['/']);

        // Return false to prevent access to the system module
        return false;
    }
  
}
