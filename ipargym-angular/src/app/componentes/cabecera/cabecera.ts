import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { JwtService } from '../../servicios/jwt';
@Component({ selector: 'app-cabecera', standalone: true, imports: [RouterLink], templateUrl: './cabecera.html' })
export class Cabecera {
  jwt = inject(JwtService);
  private router = inject(Router);
  salir() { this.jwt.cerrarSesion(); this.router.navigate(['/']); }
}
