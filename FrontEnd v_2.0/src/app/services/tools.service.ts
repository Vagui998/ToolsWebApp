import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tools } from '../models/tool';

/**
 * @class ToolsService
 * @description Servicio para realizar operaciones relacionadas con herramientas.
 */
@Injectable({
  providedIn: 'root'
})
export class ToolsService {
  private tools: Tools[] = [];

  /**
   * @constructor
   * @description Constructor del servicio ToolsService.
   * @param {HttpClient} http - Cliente HTTP para realizar las solicitudes.
   */
  constructor(private http: HttpClient) {}

  /**
   * @description Obtiene las herramientas paginadas según los parámetros especificados.
   * @param {number} page - Número de página.
   * @param {number} size - Tamaño de la página.
   * @param {string} sortField - Campo para ordenar las herramientas.
   * @param {boolean} ascending - Indicador de orden ascendente o descendente.
   * @returns {Observable<PaginatedResponse>} Observable que emite la respuesta paginada.
   */
  getPaginatedTools(
    page: number,
    size: number,
    sortField: string,
    ascending: boolean
  ): Observable<PaginatedResponse> {
    const url = 'http://localhost:1234/tools/pages';

    // Obtener el token desde la cookie
    const token = this.getCookie('token');

    // Crear encabezados y agregar el token como encabezado de autorización
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`
    });

    // Establecer los parámetros de consulta para la paginación y el ordenamiento
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('order', sortField)
      .set('asc', ascending.toString());

    return this.http.get<PaginatedResponse>(url, { headers, params });
  }

  /**
   * @description Elimina una herramienta según su identificador.
   * @param {number} id - Identificador de la herramienta a eliminar.
   * @returns {Observable<void>} Observable que emite el resultado de la eliminación.
   */
  deleteTool(id: number): Observable<void> {
    const url = `http://localhost:1234/tools/deleteEntry/${id}`;

    // Obtener el token desde la cookie
    const token = this.getCookie('token');

    // Crear encabezados y agregar el token como encabezado de autorización
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`
    });

    return this.http.delete<void>(url, { headers });
  }

  /**
   * @description Agrega una nueva herramienta.
   * @param {any} formattedTool - Herramienta formateada para agregar.
   * @returns {Observable<void>} Observable que emite el resultado de la adición.
   */
  addTool(formattedTool: any): Observable<void> {
    const url = 'http://localhost:1234/tools/newEntry';

    // Obtener el token desde la cookie
    const token = this.getCookie('token');

    // Crear encabezados y agregar el token como encabezado de autorización
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`
    });

    return this.http.post<void>(url, formattedTool, { headers });
  }

  /**
   * @description Edita una herramienta existente.
   * @param {any} formattedTool - Herramienta formateada para editar.
   * @returns {Observable<void>} Observable que emite el resultado de la edición.
   */
  editTool(formattedTool: any): Observable<void> {
    const url = 'http://localhost:1234/tools/updateEntry';

    // Obtener el token desde la cookie
    const token = this.getCookie('token');

    // Crear encabezados y agregar el token como encabezado de autorización
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`
    });

    return this.http.put<void>(url, formattedTool, { headers });
  }

  /**
   * @description Realiza una búsqueda de herramientas por término de búsqueda.
   * @param {string} term - Término de búsqueda.
   * @returns {Tools[]} Arreglo de herramientas que coinciden con el término de búsqueda.
   */
  searchTools(term: string): Tools[] {
    term = term.toLowerCase();

    const toolsArr: Tools[] = this.tools.filter((t: Tools) => {
      const name = t.name.toLowerCase();
      const brand = t.brand.name.toLowerCase();
      return name.indexOf(term) >= 0 || brand.indexOf(term) >= 0;
    });

    return toolsArr;
  }

  /**
   * @private
   * @description Obtiene el valor de una cookie por su nombre.
   * @param {string} name - Nombre de la cookie.
   * @returns {string} Valor de la cookie.
   */
  private getCookie(name: string): string {
    const cookieValue = document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)');
    return cookieValue ? cookieValue.pop() || '' : '';
  }
}

/**
 * @interface PaginatedResponse
 * @description Interfaz que representa la respuesta paginada del servidor.
 */
interface PaginatedResponse {
  content: Tools[];
  empty: boolean;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  pageable: any;
  size: number;
  sort: any;
  totalElements: number;
  totalPages: number;
}
