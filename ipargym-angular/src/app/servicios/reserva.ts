import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from '../api';
import { JwtService } from './jwt';
import { Reserva } from '../modelos/reserva';
@Injectable({ providedIn: 'root' })
export class ReservaService {
  private http = inject(HttpClient);
  private jwt = inject(JwtService);
  private url = `${API_URL}/reservas`;
  reservar(idActividad: number) { return this.http.post<Reserva>(this.url, { idActividad }, { headers: this.jwt.getHeaders() }); }
  misReservas() { return this.http.get<Reserva[]>(this.url, { headers: this.jwt.getHeaders() }); }
  cancelar(id: number) { return this.http.delete<void>(`${this.url}/${id}`, { headers: this.jwt.getHeaders() }); }
}
