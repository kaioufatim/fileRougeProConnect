import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgSelectOption } from '@angular/forms';
import { Observable } from 'rxjs';
import { UserstorageService } from 'src/app/basic/services/storage/userstorage.service';

const BASIC_URL = "http://localhost:8080/"
@Injectable({
  providedIn: 'root'
})
export class CreateurService {

  constructor(private http: HttpClient){}


  PostProject(projectDTO:any) :Observable<any>{
    const userId = UserstorageService.getUserId();
    return this.http.post(BASIC_URL+`api/createur/project/${userId}`, projectDTO, {
      headers:this.createAuthorizationheader()
    })
  
  }

  getAllProjectsByUserId():Observable<any>{
    const userId=UserstorageService.getUserId();
    return this.http.get(BASIC_URL+`api/createur/projects/${userId}`,{
      headers: this.createAuthorizationheader()
    })
    
  }

  getProjectById(projectId:any):Observable<any>{
    return this.http.get(BASIC_URL+`api/createur/project/${projectId}`,{
      headers: this.createAuthorizationheader()
    })
    
  }
  updateProject(projectId:any,projectDTO:any): Observable<any>{
    return this.http.put(BASIC_URL+`api/createur/project/${projectId}`, projectDTO, {
      headers : this.createAuthorizationheader()
    })
  }

  deleteProject(projectId:any): Observable<any>{
    return this.http.delete(BASIC_URL+`api/createur/project/${projectId}`, {
      headers : this.createAuthorizationheader()
    })
  }
  getAllProjectReservation():Observable<any>{
    const createurId = UserstorageService.getUserId();
    return this.http.get(BASIC_URL+`api/createur/propositions/${createurId}`,{
      headers: this.createAuthorizationheader()
    })
    
  }

  changeReservatinStaus(reservationId:number,status:string):Observable<any>{
    return this.http.get(BASIC_URL+`api/createur/propositions/${reservationId}/${status}`,{
      headers: this.createAuthorizationheader()
    })
    
  }

  createAuthorizationheader():HttpHeaders{
   // let autheaders: HttpHeaders=new HttpHeaders()
 // return autheaders.set('Autorization',
 // 'Bearer ' +UserstorageService.getToken() )
 let headers: HttpHeaders = new HttpHeaders();
 return headers.set('Authorization', 'Bearer ' + UserstorageService.getToken());
  }

}
