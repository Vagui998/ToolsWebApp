/**
 * Componente que representa la página Acerca de Nosotros.
 */
import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { User } from '../models/user';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  listOfUsers:User[] = []
  pageSize = 2
  from = 0
  until = 2

  /**
   * Constructor del componente AboutComponent.
   */
  constructor() { }

  ngOnInit() {
    //this.listOfUsers = this._userService.getAllUsers()
   }

   changePage(e: PageEvent) {
    this.from = e.pageIndex * e.pageSize
    this.until = this.from + e.pageSize
   }


   delete() {

   }

}
