import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from '../../servicios/usuario';
import { JwtService } from '../../servicios/jwt';
@Component({ selector: 'app-login', standalone: true, imports: [FormsModule], templateUrl: './login.html' })
export class Login {
  private servicio = inject(UsuarioService); private jwt = inject(JwtService); private router = inject(Router);
  email = ''; password = ''; error = signal(''); procesando = signal(false);
  login() {
    this.error.set(''); this.procesando.set(true);
    this.servicio.login({ email: this.email, password: this.password }).subscribe({
      next: r => { this.jwt.guardarToken(r.accessToken); this.procesando.set(false); this.router.navigate(['/']); },
      error: e => { this.error.set(e.status === 401 ? 'Email o contraseña incorrectos' : 'No se ha podido iniciar sesión'); this.procesando.set(false); }
    });
  }
}
