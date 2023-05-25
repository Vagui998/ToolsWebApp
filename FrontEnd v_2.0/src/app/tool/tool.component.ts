import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ToolsService } from '../services/tools.service';
import { Tools } from '../models/tool';

const elementsPerPage = 3;

/**
 * @class ToolComponent
 * @description Componente para mostrar y navegar herramientas.
 */
@Component({
  selector: 'app-tool',
  templateUrl: './tool.component.html',
  styleUrls: ['./tool.component.css']
})
export class ToolComponent implements OnInit, OnDestroy {
  listOfTools: Tools[] = [];
  page: number = 0;
  totalItems: number = 0;
  private subscription: Subscription = new Subscription();

  /**
   * @constructor
   * @description Constructor del componente ToolComponent.
   * @param {Router} router - Enrutador para la navegación.
   * @param {ToolsService} toolService - Servicio para realizar operaciones con herramientas.
   */
  constructor(public router: Router, private toolService: ToolsService) {}

  /**
   * @description Método de inicialización del componente.
   */
  ngOnInit() {
    this.fetchTools();
  }

  /**
   * @description Método de destrucción del componente.
   */
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  /**
   * @description Recupera las herramientas paginadas desde el servidor.
   */
  fetchTools() {
    this.subscription = this.toolService
      .getPaginatedTools(this.page, elementsPerPage, 'id', true)
      .subscribe(
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
   * @description Navega a la página anterior de herramientas.
   */
  previousPage() {
    if (this.page >= 1) {
      this.page--;
      this.fetchTools();
    }
  }

  /**
   * @description Navega a la siguiente página de herramientas.
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
   * @description Navega para ver los detalles de una herramienta específica.
   * @param {number} idx - Índice de la herramienta.
   */
  seeTool(idx: number) {
    this.router.navigate(['/tool', idx]);
  }
}
