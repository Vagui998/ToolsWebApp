import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { ToolsService } from '../services/tools.service';

@Component({
  selector: 'app-searcher',
  templateUrl: './searcher.component.html',
  styleUrls: ['./searcher.component.css']
})


/**
 * El componente ToolComponent se encarga  de buscar herramientas en una lista a partir de un término de
 * búsqueda obtenido de la ruta, y mostrar los resultados en la vista.
 */
export class SearcherComponent implements OnInit {

   /**
   * Arreglo que contendrá las herramientas que coincidan con el término de búsqueda.
   */
   tools:any[] = [];

   /**
    * Término de búsqueda obtenido de la ruta.
    */
   term:string = '';

  constructor( private activatedRoute:ActivatedRoute, private router:Router, private toolsService: ToolsService) {}

  /**
   * Este método se ejecuta cuando se inicializa el componente.
   * Obtiene el término de búsqueda de la ruta y utiliza el servicio para buscar las herramientas que coincidan.
   * @returns void
   */
  ngOnInit(): void {
      // Suscribe el componente al evento de cambio de parámetros de ruta y recibe el parámetro 'term'
      this.activatedRoute.params.subscribe (params => {
        this.term = params['term']; // Obtiene el término de búsqueda de la ruta
        this.tools = this.toolsService.searchTools( params['term']); // Busca las herramientas que coinciden con el término de búsqueda usando el servicio
      })
  }

  /**
   * Este método redirige al usuario a la página de detalles de una herramienta específica.
   * @param idx Número de índice de la herramienta en el arreglo.
   * @returns void
   */
  seeTool(idx: number) {
    this.router.navigate(['/tool', idx]); // Navega a la página de detalles de la herramienta específica usando el enrutador
  }

}
