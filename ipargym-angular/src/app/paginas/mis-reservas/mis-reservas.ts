import { Component, inject, OnInit, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Reserva } from '../../modelos/reserva';
import { ReservaService } from '../../servicios/reserva';
@Component({ selector: 'app-mis-reservas', standalone: true, imports: [DatePipe, RouterLink], templateUrl: './mis-reservas.html' })
export class MisReservas implements OnInit {
  private servicio = inject(ReservaService);
  reservas = signal<Reserva[]>([]); mensaje = signal(''); error = signal(''); cargando = signal(true);
  ngOnInit() { this.servicio.misReservas().subscribe({ next: r => { this.reservas.set(r); this.cargando.set(false); }, error: e => { this.error.set(e.error?.detail ?? 'Error al cargar reservas'); this.cargando.set(false); } }); }
  cancelar(id: number) {
    if (!confirm('¿Deseas cancelar esta reserva?')) return;
    this.error.set(''); this.mensaje.set('');
    this.servicio.cancelar(id).subscribe({ next: () => { this.reservas.update(r => r.filter(x => x.id !== id)); this.mensaje.set('Reserva cancelada correctamente'); }, error: e => this.error.set(e.error?.detail ?? 'Error al cancelar la reserva') });
  }
}
