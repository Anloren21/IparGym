import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from '../api';
import { Login, Token } from '../modelos/token';
@Injectable({ providedIn: 'root' })
export class UsuarioService {
  private http = inject(HttpClient);
  login(datos: Login) { return this.http.post<Token>(`${API_URL}/usuarios/login`, datos); }
}
