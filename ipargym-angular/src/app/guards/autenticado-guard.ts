import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { JwtService } from '../servicios/jwt';
export const autenticadoGuard: CanActivateFn = () => {
  const jwt = inject(JwtService); const router = inject(Router);
  return jwt.autenticado() ? true : router.createUrlTree(['/login']);
};
