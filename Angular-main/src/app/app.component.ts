import { Component } from '@angular/core';
import { UserstorageService } from './basic/services/storage/userstorage.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'projectfront';
  iscreateurLoggedIn :boolean = UserstorageService.iscreateurLoggedIn();
  isentrepreneurLoggedIn :boolean = UserstorageService.isentrepreneurLoggedIn();

  constructor(private router:Router){}

  ngOnInit(){
    this.router.events.subscribe(event =>{
      this.iscreateurLoggedIn = UserstorageService.iscreateurLoggedIn();
      this.isentrepreneurLoggedIn=UserstorageService.isentrepreneurLoggedIn();
      
    })
  }
  
  logout(){
    UserstorageService.signOut();
    this.router.navigateByUrl('login');
  }
}
