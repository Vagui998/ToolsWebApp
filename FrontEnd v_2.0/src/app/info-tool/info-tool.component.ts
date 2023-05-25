/**
 * Componente que representa la página de información de una herramienta.
 */
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-info-tool',
  templateUrl: './info-tool.component.html',
  styleUrls: ['./info-tool.component.css']
})
export class InfoToolComponent {

  /**
   * Objeto que contiene la información de la herramienta.
   */
  tool: any = {};

  /**
   * Constructor del componente InfoToolComponent.
   *
   * @param activateRoute El ActivatedRoute utilizado para obtener los parámetros de la URL.
   * @param _toolService El servicio que se utiliza para obtener la información de la herramienta.
   */
  constructor(private activateRoute: ActivatedRoute) {

    // Obtiene el parámetro 'id' de la URL y utiliza el servicio para obtener la información de la herramienta correspondiente.
    this.activateRoute.params.subscribe(params => {
      //this.tool = this._toolService.getTool(params['id']);
    });
  }

  /**
   * Método que se ejecuta al inicializar el componente.
   * En este caso no tiene implementación.
   * @returns void
   */
  ngOnInit() {
    // ...
  }

}
