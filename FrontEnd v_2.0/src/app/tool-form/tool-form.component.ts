import { Component } from '@angular/core';
import { Tools } from '../models/tool';
import { ActivatedRoute, Router } from '@angular/router';
import { ToolsService } from '../services/tools.service';

/**
 * @class ToolFormComponent
 * @description Componente para crear y actualizar herramientas.
 */
@Component({
  selector: 'app-tool-form',
  templateUrl: './tool-form.component.html',
  styleUrls: ['./tool-form.component.css']
})
export class ToolFormComponent {

  tool: Tools = new Tools();
  isUpdate: boolean = false;
  selectedToolId: number = 0;

  /**
   * @constructor
   * @description Constructor del componente ToolFormComponent.
   * @param {ActivatedRoute} activatedRoute - Proveedor de información sobre la ruta activada.
   * @param {ToolsService} toolsService - Servicio para realizar operaciones con herramientas.
   * @param {Router} router - Enrutador para la navegación.
   */
  constructor(
    private activatedRoute: ActivatedRoute,
    private toolsService: ToolsService,
    private router: Router
  ) {}

  /**
   * @description Método de inicialización del componente.
   */
  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.selectedToolId = +params['id'];
      this.isUpdate = this.selectedToolId > 0;
    });
  }

  /**
   * @description Crea una nueva herramienta.
   */
  create(): void {
    const formattedTool = {
      name: this.tool.name,
      image_url: this.tool.imageURL,
      description: this.tool.description,
      brand: {
        id: this.tool.brand.id
      },
      price: this.tool.price
    };

    this.toolsService.addTool(formattedTool).subscribe(() => {
      alert('Herramienta creada exitosamente.');
      this.goBack();
      // Handle success, e.g., show a success message
    }, (error) => {
      alert('Sin autorización, por favor inicie sesión.');
      // Handle error, e.g., show an error message
    });
  }

  /**
   * @description Actualiza una herramienta existente.
   */
  update(): void {
    const formattedTool = {
      id: this.selectedToolId, // Add the ID of the selected tool
      name: this.tool.name,
      image_url: this.tool.imageURL,
      description: this.tool.description,
      brand: {
        id: this.tool.brand.id
      },
      price: this.tool.price
    };

    this.toolsService.editTool(formattedTool).subscribe(() => {
      alert('Herramienta actualizada exitosamente.');
      this.goBack();
      // Handle success, e.g., show a success message
    }, (error) => {
      alert('Sin autorización, por favor inicie sesión.');
      // Handle error, e.g., show an error message
    });
  }

  /**
   * @description Navega hacia atrás en la aplicación.
   */
  goBack(): void {
    // Get the previous URL from the activated route
    const previousUrl = this.activatedRoute.snapshot.queryParams['returnUrl'] || '/';

    // Navigate back to the previous URL
    this.router.navigateByUrl(previousUrl);
  }

}
