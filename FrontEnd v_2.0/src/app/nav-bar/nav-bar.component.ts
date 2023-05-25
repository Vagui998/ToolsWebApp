/**
 * Componente que representa la barra de navegación de la aplicación.
 */
import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})


/**
 * La clase NavBarComponent es un componente que representa la barra de navegación de la aplicación.
 */
export class NavBarComponent {

  /**
   * Constructor de la clase NavBarComponent.
   *
   * @param router El servicio de enrutamiento de Angular.
   * @param toolsService El servicio que maneja las herramientas de la aplicación.
   */
  constructor( private router:Router) {

  }

  /**
   * Navega a la página de búsqueda de herramientas con el término especificado.
   *
   * @param term El término de búsqueda especificado por el usuario.
   */
  searchTool( term:string) {
    this.router.navigate(['/search', term]);
  }

}
