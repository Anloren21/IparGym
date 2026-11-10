import { Routes } from '@angular/router';
import { Principal } from './paginas/principal/principal';
import { Login } from './paginas/login/login';
import { ActividadDetalle } from './paginas/actividad-detalle/actividad-detalle';
import { MisReservas } from './paginas/mis-reservas/mis-reservas';
import { Admin } from './paginas/admin/admin';
import { autenticadoGuard } from './guards/autenticado-guard';
import { adminGuard } from './guards/admin-guard';
export const routes: Routes = [
  { path: '', component: Principal },
  { path: 'login', component: Login },
  { path: 'actividad/:id', component: ActividadDetalle, canActivate: [autenticadoGuard] },
  { path: 'reservas', component: MisReservas, canActivate: [autenticadoGuard] },
  { path: 'admin', component: Admin, canActivate: [adminGuard] },
  { path: '**', redirectTo: '' }
];
