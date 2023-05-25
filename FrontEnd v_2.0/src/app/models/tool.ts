import { Brand } from '../models/brand';

/**
 * @class Tools
 * @description Clase que representa una herramienta.
 */
export class Tools {
  /**
   * @description Identificador de la herramienta.
   */
  id: number;

  /**
   * @description URL de la imagen de la herramienta.
   */
  imageURL: string;

  /**
   * @description Marca de la herramienta.
   */
  brand: Brand;

  /**
   * @description Descripción de la herramienta.
   */
  description: string;

  /**
   * @description Nombre de la herramienta.
   */
  name: string;

  /**
   * @description Precio de la herramienta.
   */
  price: number;

  /**
   * @constructor
   * @description Constructor de la clase Tools.
   * Inicializa los valores predeterminados de los atributos.
   */
  constructor() {
    this.id = 0;
    this.imageURL = '';
    this.brand = new Brand('', 0);
    this.description = '';
    this.name = '';
    this.price = 0.0;
  }
}
