import { Injectable } from '@angular/core';


const TOKEN ='$_token';
const USER ='$_user';

@Injectable({
  providedIn: 'root'
})
export class UserstorageService {

  constructor() { }
  public saveToken(token : string): void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN,token);
  }
  static getToken(): string {
    return localStorage.getItem(TOKEN);

  }
  public saveUser(user:any): void{
      window.localStorage.removeItem(USER);
      window.localStorage.setItem(USER, JSON.stringify(user));
     
  //  window.localStorage.removeItem(USER);
   // window.localStorage.setItem(USER,JSON.stringify(user));
  }
  static getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }
  static getUserId(): string{
    const user = this.getUser();
    if(user === null){
      return '';
    }
    return user.userId;
  }
  static getUserRole(): string{
    const user = this.getUser();
    if(user === null){
      return '';
    }
    return user.role;
//const user = this.getUser();
 //if (user === null || !user.roles || user.roles.length === 0) {
  //  return ''; 
 //}
   //   return user.roles[0];
  }
//  static isentrepreneurLoggedIn():boolean{
  //  if(this.getToken() === null){
   //   return false;
    //}
    //const role:string= this.getUserRole();
    //return role == 'ROLE_ENTREPRENEUR'
 // }
  static iscreateurLoggedIn2():boolean{
    if(this.getToken() === null){
      return false;
    }
    const role:string= this.getUserRole();
    return role == 'ROLE_CREATEUR'
  }

  static isentrepreneurLoggedIn(): boolean {
    if (this.getToken() === null || !this.getUser()) {
       return false;
    }
    const role: string = this.getUserRole();
    return role === 'ROLE_ENTREPRENEUR';
   }
   
   static iscreateurLoggedIn(): boolean {
    if (this.getToken() === null || !this.getUser()) {
       return false;
    }
    const role: string = this.getUserRole();
    return role === 'ROLE_CREATEUR';
   }
   
  static signOut() : void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }

}
