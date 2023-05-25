import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import jwt_decode from 'jwt-decode';

// Interfaz para el token JWT decodificado
interface JwtToken {
  sub: string;
  iat: number;
  exp: number;
}

/**
 * @description Componente de inicio de sesión.
 * Permite a los usuarios autenticarse y obtener un token de acceso.
 */
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  /**
   * @description Nombre de usuario introducido por el usuario.
   */
  username: string = '';

  /**
   * @description Contraseña introducida por el usuario.
   */
  password: string = '';

  constructor(private http: HttpClient, private cookieService: CookieService) {}

  /**
   * @description Método que se ejecuta al intentar iniciar sesión.
   * Envía las credenciales de usuario al servidor para autenticación.
   */
  login() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    console.log(`username: ${this.username}, password: ${this.password}`);
    const credentials = {
      username: this.username,
      password: this.password
    };

    this.http.post<any>('http://localhost:11234/api/v1/auth/authenticate', credentials, { headers })
      .subscribe(
        response => {
          const token = response.access_token;
          const refreshToken = response.refresh_token;

          // Almacenar los tokens en cookies
          this.cookieService.set('token', token);
          this.cookieService.set('refreshToken', refreshToken);

          console.log(response);

          const decodedToken: JwtToken = jwt_decode(token);
          this.username = decodedToken.sub;
          alert(`Has iniciado sesión como ${this.username}`);
        },
        error => {
          alert('Credenciales incorrectas');
        }
      );
  }
}
