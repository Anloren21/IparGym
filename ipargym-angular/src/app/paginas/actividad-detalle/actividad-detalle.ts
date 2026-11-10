import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { DatePipe } from '@angular/common';
import { ActividadDetalle as Modelo } from '../../modelos/actividad';
import { ActividadService } from '../../servicios/actividad';
import { ReservaService } from '../../servicios/reserva';
@Component({ selector: 'app-actividad-detalle', standalone: true, imports: [DatePipe, RouterLink], templateUrl: './actividad-detalle.html' })
export class ActividadDetalle implements OnInit {
  private route = inject(ActivatedRoute); private actividadService = inject(ActividadService); private reservaService = inject(ReservaService);
  actividad = signal<Modelo | null>(null); mensaje = signal(''); error = signal(''); procesando = signal(false);
  ngOnInit() { this.cargar(Number(this.route.snapshot.paramMap.get('id'))); }
  cargar(id: number) { this.actividadService.buscarActividadPorId(id).subscribe({ next: a => this.actividad.set(a), error: e => this.error.set(e.error?.detail ?? 'No se pudo cargar la actividad') }); }
  reservar(id: number) {
    this.error.set(''); this.mensaje.set(''); this.procesando.set(true);
    this.reservaService.reservar(id).subscribe({ next: () => { this.mensaje.set('Reserva realizada correctamente'); this.procesando.set(false); this.cargar(id); }, error: e => { this.error.set(e.error?.detail ?? 'No se pudo realizar la reserva'); this.procesando.set(false); } });
  }
}
