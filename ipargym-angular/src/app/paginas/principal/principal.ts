import { Component, inject, OnInit, signal } from '@angular/core';
import { Router } from '@angular/router';
import { ActividadFila } from '../../componentes/actividad-fila/actividad-fila';
import { ActividadListado } from '../../modelos/actividad';
import { ActividadService } from '../../servicios/actividad';
@Component({ selector: 'app-principal', standalone: true, imports: [ActividadFila], templateUrl: './principal.html' })
export class Principal implements OnInit {
  private servicio = inject(ActividadService); private router = inject(Router);
  actividades = signal<ActividadListado[]>([]); error = signal(''); cargando = signal(true);
  ngOnInit() { this.servicio.listarActividades().subscribe({ next: datos => { this.actividades.set(datos); this.cargando.set(false); }, error: () => { this.error.set('No se pudieron cargar las actividades'); this.cargando.set(false); } }); }
  verDetalle(id: number) { this.router.navigate(['/actividad', id]); }
}
