import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AccessToken } from '../models/access-token';
import { User } from '../models/user';
import { map, tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    // User cache
    private user?: User;

    // Access token
    private token?: AccessToken;

    public constructor(
        private storage: Storage,
        private http: HttpClient
    ) {

        // Si hay token en localStorage
        const item: string | null = this.storage.getItem(environment.token_item);

        // Si el item existe
        if (item) {
            // Leer objeto
            this.token = JSON.parse(item);

            // Si hay token
            if (this.token) {

                // Si hay expiracion
                if (this.token.expiracion) {
                    // Transformar fecha a Date
                    this.token.expiracion = new Date(this.token.expiracion);
                } else {
                    // Eliminar token
                    this.cleanToken();
                }
            }
        }
    }

    public login(user: User): Observable<boolean> {
        // Si no hay contraseña
        if (!user.password) {
            // Retornar falso
            return of(false);
        }

        // Crear FormData
        const form: FormData = new FormData();

        // Método de inicio de sesión
        form.append('grant_type', 'password');

        // Usuario
        form.append('username', user.username);
        form.append('password', user.password);

        // Enviar FormData
        return this.http.post<AccessToken>(`${environment.host}/oauth/token`, form)
            .pipe(map(
                response => {
                    // Fijar token
                    this.setToken(response);

                    // Retornar verdadero
                    return true;
                },
                () => {
                    // Retornar falso
                    return false;
                }
            ));
    }

    public getToken(): AccessToken | undefined {
        // Si hay token en memoria
        if (this.token) {

            // Retornar copia del token
            return Object.assign({}, this.token);
        }

        // Retornar indefinido
        return undefined;
    }

    public setToken(token: AccessToken): AccessToken | undefined {
        // Si el token no es nulo
        if (token != null) {
            // Copiar token a memoria
            this.token = Object.assign({}, token);

            // Si no hay fecha de expiracion
            if (!this.token.expiracion) {
                // Fijar expiración
                this.token.expiracion = new Date(Date.now() + token.expires_in * 1000);
            }

            // Almacenar en localStorage
            this.storage.setItem(environment.token_item, JSON.stringify(this.token));

            // Eliminar usuario de memoria
            this.user = undefined;

            // Retornar copia del token
            return Object.assign({}, this.token);
        }

        // Returnar indefinido
        return undefined;
    }

    public cleanToken(): void {
        // Quitar token de memoria
        this.token = undefined;

        // Quitar token de localStorage
        this.storage.removeItem(environment.token_item);

        // Eliminar usuario de memoria
        this.user = undefined;
    }

    public getUser(): Observable<User> {
        // Si hay un usuario
        if (this.user) {
            // Retornar un observable de ese usuario
            return of(this.user);
        }

        // Retornar un observable de una petición que obtiene al usuario
        return this.http.get<User>(`${environment.host}/usuarios/me`)
            .pipe(tap(response => this.user = response));
    }

    public isTokenValid(): boolean {
        // Si hay token
        if (this.token && this.token.expiracion) {
            // Verificar expiración
            return this.token.expiracion.getTime() > Date.now();
        }

        // No hay token
        return false;
    }

}
