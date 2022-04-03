import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthService} from "../services/auth.service";
import {environment} from "../../environments/environment";
import {switchMap} from "rxjs/operators";
import {AccessToken} from "../interfaces/access-token";

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  public constructor(
    private authService: AuthService
  ) {
  }

  public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    // If the requested endpoint is for generating an access token,
    if (request.url.startsWith(`${environment.host}/oauth2/token`)) {

      // Process the request normally
      return next.handle(request);
    }

    // If the requested endpoint is for a protected resource,
    if (request.url.startsWith(environment.host)) {

      // If the user is authenticated
      return this.authService.isAuthenticated()
        .pipe(switchMap(
          (isAuthenticated: boolean) => {

            // If there's no authentication
            if (!isAuthenticated) {

              // Process the request normally
              return next.handle(request);
            }

            // Get authenticated user from localStorage
            const accessToken: AccessToken | undefined = this.authService.getToken();

            // If there is a token
            if (accessToken) {

              // Create a new request with the token
              const headers: HttpHeaders = request.headers.set('Authorization', `Bearer ${accessToken.access_token}`);

              // Copy the original request and replace the headers
              const newRequest: HttpRequest<any> = request.clone({
                headers
              });

              // Send the new request
              return next.handle(newRequest);
            }

            // Return request
            return next.handle(request);
          }
        ));
    }

    // Send the request
    return next.handle(request);
  }

}
