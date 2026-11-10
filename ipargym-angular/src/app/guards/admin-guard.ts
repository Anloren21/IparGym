import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { JwtService } from '../servicios/jwt';
export const adminGuard: CanActivateFn = () => {
  const jwt = inject(JwtService); const router = inject(Router);
  if (!jwt.autenticado()) return router.createUrlTree(['/login']);
  return jwt.rol() === 'ADMINISTRADOR' ? true : router.createUrlTree(['/']);
};
