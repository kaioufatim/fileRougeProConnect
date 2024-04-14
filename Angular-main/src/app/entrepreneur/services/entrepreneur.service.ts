import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserstorageService } from 'src/app/basic/services/storage/userstorage.service';

const BASIC_URL ="http://localhost:8080/"
@Injectable({
  providedIn: 'root'
})
export class EntrepreneurService {

  constructor(private http:HttpClient) { }

  getAllProjects():Observable<any>{
    return this.http.get(BASIC_URL+`api/entrepreneur/projects/`,{
      headers: this.createAuthorizationheader()
    })
    
  }

  searchProjectByName(name:any):Observable<any>{
    return this.http.get(BASIC_URL+`api/entrepreneur/search/${name}`,{
      headers: this.createAuthorizationheader()
    })
    
  }

  getProjectdetailsByProjectId(projectId:any):Observable<any>{
    return this.http.get(BASIC_URL+`api/entrepreneur/project/${projectId}`,{
      headers: this.createAuthorizationheader()
    })
    
  }

  propositionService(propositionDto:any):Observable<any>{
    return this.http.post(BASIC_URL+`api/entrepreneur/proposition-service`, propositionDto ,{
      headers: this.createAuthorizationheader()
    })
    
  }

  getMyReservations():Observable<any>{
    const userId =UserstorageService.getUserId();
    return this.http.get(BASIC_URL+`api/entrepreneur/my-propositions/${userId}`,{
      headers: this.createAuthorizationheader()
    })
    
  }

  giveReview(reviewDto:any):Observable<any>{
    return this.http.post(BASIC_URL+`api/entrepreneur/review`, reviewDto ,{
      headers : this.createAuthorizationheader()
    })
    
  }

  createAuthorizationheader():HttpHeaders{
  let headers: HttpHeaders = new HttpHeaders();
  return headers.set('Authorization', 'Bearer ' + UserstorageService.getToken());
   }
}
