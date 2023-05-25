/**
 * @class User
 * @description Clase que representa un usuario.
 */
export class User {

  /**
   * @description Identificador del usuario.
   */
  id: number;

  /**
   * @description Nombre del usuario.
   */
  name: string;

  /**
   * @description Apellido del usuario.
   */
  lastName: string;

  /**
   * @description Rol del usuario.
   */
  role: string;

  /**
   * @constructor
   * @description Constructor de la clase User.
   * Inicializa los valores predeterminados de los atributos.
   */
  constructor() {
    this.id = 0;
    this.name = '';
    this.lastName = '';
    this.role = '';
  }
}
