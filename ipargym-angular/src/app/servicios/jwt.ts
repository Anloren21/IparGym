import { computed, Injectable, signal } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { JwtPayload } from '../modelos/token';

@Injectable({ providedIn: 'root' })
export class JwtService {
  private tokenSignal = signal<string | null>(sessionStorage.getItem('token'));
  private ahora = signal(Date.now());
  readonly token = this.tokenSignal.asReadonly();
  private payload = computed<JwtPayload | null>(() => {
    try {
      const parte = this.tokenSignal()?.split('.')[1];
      if (!parte) return null;
      const base64 = parte.replace(/-/g, '+').replace(/_/g, '/');
      const bytes = Uint8Array.from(atob(base64), c => c.charCodeAt(0));
      return JSON.parse(new TextDecoder().decode(bytes)) as JwtPayload;
    } catch { return null; }
  });
  readonly autenticado = computed(() => {
    const p = this.payload();
    return !!p && p.exp * 1000 > this.ahora();
  });
  readonly nombre = computed(() => this.autenticado() ? this.payload()?.nombre ?? '' : '');
  readonly rol = computed(() => this.autenticado() ? this.payload()?.rol ?? '' : '');
  constructor() { setInterval(() => this.ahora.set(Date.now()), 1000); }
  guardarToken(token: string): void {
    sessionStorage.setItem('token', token);
    this.ahora.set(Date.now());
    this.tokenSignal.set(token);
  }
  cerrarSesion(): void {
    sessionStorage.removeItem('token');
    this.tokenSignal.set(null);
  }
  getHeaders(): HttpHeaders {
    this.ahora.set(Date.now());
    const token = this.tokenSignal();
    return new HttpHeaders(token && this.autenticado() ? { Authorization: `Bearer ${token}` } : {});
  }
}
