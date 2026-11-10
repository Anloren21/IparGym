import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActividadCrear } from '../../modelos/actividad';
import { ActividadService } from '../../servicios/actividad';
@Component({ selector: 'app-admin', standalone: true, imports: [FormsModule], templateUrl: './admin.html' })
export class Admin {
  private servicio = inject(ActividadService);
  mensaje = signal(''); error = signal('');
  actividad: ActividadCrear = { nombre: '', descripcion: '', monitor: '', fechaHora: '', duracionMinutos: 60, plazas: 10 };
  crear() {
    this.mensaje.set(''); this.error.set('');
    this.servicio.crearActividad(this.actividad).subscribe({ next: () => { this.mensaje.set('Actividad creada correctamente'); this.actividad = { nombre: '', descripcion: '', monitor: '', fechaHora: '', duracionMinutos: 60, plazas: 10 }; }, error: e => this.error.set(e.error?.detail ?? 'Error al crear la actividad') });
  }
}
