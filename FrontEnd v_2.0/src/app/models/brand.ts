/**
 * @class Brand
 * @description Clase que representa una marca.
 */
export class Brand {

  /**
   * @description Identificador de la marca.
   */
  id: number;

  /**
   * @description Nombre de la marca.
   */
  name: string;

  /**
   * @constructor
   * @description Constructor de la clase Brand.
   * @param {string} name - Nombre de la marca.
   * @param {number} id - Identificador de la marca.
   */
  constructor(name: string, id: number) {
    this.name = name;
    this.id = id;
  }

}
