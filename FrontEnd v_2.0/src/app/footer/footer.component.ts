/**
 * Componente que representa el pie de página.
 */
import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  /**
   * Variable que indica el año actual.
   */
  anio: number;

  /**
   * Constructor del componente FooterComponent.
   */
  constructor() {
    // Obtenemos el año actual
    this.anio = new Date().getFullYear();
  }

  /**
   * Función que se ejecuta cuando se inicializa el componente.
   */
  ngOnInit() {
    // ...
  }

}
