import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from '../api';
import { JwtService } from './jwt';
import { ActividadListado, ActividadDetalle, ActividadCrear } from '../modelos/actividad';
@Injectable({ providedIn: 'root' })
export class ActividadService {
  private http = inject(HttpClient);
  private jwt = inject(JwtService);
  private url = `${API_URL}/actividades`;
  listarActividades() { return this.http.get<ActividadListado[]>(this.url); }
  buscarActividadPorId(id: number) { return this.http.get<ActividadDetalle>(`${this.url}/${id}`, { headers: this.jwt.getHeaders() }); }
  crearActividad(datos: ActividadCrear) { return this.http.post<ActividadDetalle>(this.url, datos, { headers: this.jwt.getHeaders() }); }
}
