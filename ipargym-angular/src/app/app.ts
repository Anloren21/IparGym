import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Cabecera } from './componentes/cabecera/cabecera';
import { Pie } from './componentes/pie/pie';
@Component({ selector: 'app-root', standalone: true, imports: [RouterOutlet, Cabecera, Pie], templateUrl: './app.html' })
export class App {}
