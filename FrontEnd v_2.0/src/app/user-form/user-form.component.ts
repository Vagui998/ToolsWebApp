import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit{

  user:User = new User()
  isUpdate: boolean = false;


  constructor(private activatedRoute:ActivatedRoute) {

  }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id) {
      this.isUpdate = true;
    }
  }

  load():void{
    this.activatedRoute.params.subscribe(
      e=>{
        let id=e['id']
        if (id) {
          //TODO
        }
      }
    )
  }

  create():void{

  }

  update():void{

  }

}

