import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToolsService } from '../services/tools.service';
import { Tools } from '../models/tool';
import { Subscription } from 'rxjs';

const elementsPerPage = 3;

/**
 * @description Componente principal que muestra una lista de herramientas.
 */
@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit, OnDestroy {
  /**
   * @description Lista de herramientas.
   */
  listOfTools: Tools[] = [];

  /**
   * @description Página actual.
   */
  page: number = 0;

  /**
   * @description Número total de elementos.
   */
  totalItems: number = 0;

  /**
   * @description Suscripción al servicio de herramientas.
   */
  private subscription: Subscription = new Subscription();

  constructor(private toolService: ToolsService, private route: ActivatedRoute) {}

  /**
   * @description Método que se ejecuta al inicializar el componente.
   * Recupera las herramientas de la página actual.
   */
  ngOnInit() {
    this.route.params.subscribe(() => {
      this.fetchTools();
    });
  }

  /**
   * @description Método que se ejecuta al destruir el componente.
   * Cancela la suscripción al servicio de herramientas.
   */
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  /**
   * @description Recupera las herramientas de la página actual mediante el servicio de herramientas.
   */
  fetchTools() {
    this.subscription = this.toolService.getPaginatedTools(this.page, elementsPerPage, 'id', true).subscribe(
      (response) => {
        this.listOfTools = response.content;
        this.totalItems = response.totalElements;
      },
      (error) => {
        console.error('Error retrieving tools:', error);
      }
    );
  }

  /**
   * @description Muestra la página anterior si existe.
   */
  previousPage() {
    if (this.page >= 1) {
      this.page--;
      this.fetchTools();
    }
  }

  /**
   * @description Muestra la página siguiente si existe.
   */
  nextPage() {
    const nextPage = this.page + 1;
    const totalPages = Math.ceil(this.totalItems / elementsPerPage);

    if (nextPage < totalPages) {
      this.page++;
      this.fetchTools();
    }
  }

  /**
   * @description Elimina una herramienta por su ID.
   * @param {number} id - ID de la herramienta a eliminar.
   */
  delete(id: number) {
    this.subscription = this.toolService.deleteTool(id).subscribe(
      () => {
        alert('Herramienta eliminada exitosamente.');
        this.listOfTools = this.listOfTools.filter(tool => tool.id !== id);

        if (this.listOfTools.length === 0 && this.page > 0) {
          this.previousPage();
        } else {
          this.fetchTools();
        }
      },
      (error) => {
        if (error.status === 401) {
          alert('Sin autorización, por favor inicie sesión.');
        } else {
          console.error('Error: ', error);
        }
      }
    );
  }
}
