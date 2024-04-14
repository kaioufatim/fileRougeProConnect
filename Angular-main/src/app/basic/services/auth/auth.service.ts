import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';
import { UserstorageService } from '../storage/userstorage.service';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';

const Basic_Url = 'http://localhost:8080/';
//export const AUTH_HEADER='authorization';
export const AUTH_HEADER = 'Authorization';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient,
    private userstorageservice:UserstorageService,
    private router : Router,
    private notification : NzNotificationService,


  ) { }
  registerCreateur(signupRequestDTO:any):Observable<any>{
    return this.http.post(Basic_Url+"createur/signUp",signupRequestDTO);
  }

  registerEntrepreneur(signupRequestDTO:any):Observable<any>{
    return this.http.post(Basic_Url+"entrepreneur/signUp",signupRequestDTO);
  }

  login(username:string,password:string){
  return this.http.post(Basic_Url+"api/auth/signin",{username,password},{observe:'response'})
  .pipe(
    map((res:HttpResponse<any>)=>{
      this.userstorageservice.saveUser(res.body);
      console.log(res.body)
      const tokenLength=res.headers.get(AUTH_HEADER)?.length;
      const bearerToken = res.headers.get(AUTH_HEADER)?.substring(7,tokenLength);
      console.log(bearerToken);
      this.userstorageservice.saveToken(bearerToken);
      const token = UserstorageService.getToken();

      console.log('Token saved:', token); // Add this line
      const responseBody = res.body;
        const user = {
          role: responseBody.roles[0],
          userId: responseBody.id
        };
      const accessToken = responseBody.accessToken;
      console.log(accessToken);
      this.userstorageservice.saveUser(user);
      console.log(user);
      this.userstorageservice.saveToken(accessToken);
   return res;
    })
  );
}

login2(username: string, password: string) {
  return this.http.post(Basic_Url + "api/auth/signin", { username, password }, { observe: 'response' })
    .pipe(
      map((res: HttpResponse<any>) => {
        const responseBody = res.body;
        const user = {
          role: responseBody.roles[0],
          userId: responseBody.id
        };
        this.userstorageservice.saveUser(user);
        console.log(user);
        const accessToken = responseBody.accessToken;
        console.log(accessToken);
        this.userstorageservice.saveToken(accessToken);
      
        return res;
      })
    );
}
login4(username: string, password: string) {
  return this.http.post(Basic_Url + "api/auth/signin", { username, password }, { observe: 'response' })
    .pipe(
      map((res: HttpResponse<any>) => {
        // Enregistrer les données de l'utilisateur dans le stockage local
        this.userstorageservice.saveUser(res.body);
        
        // Récupérer et enregistrer le jeton d'accès
        const tokenLength = res.headers.get(AUTH_HEADER)?.length;
        const bearerToken = res.headers.get(AUTH_HEADER)?.substring(7, tokenLength);
        this.userstorageservice.saveToken(bearerToken);
        
        // Extraire l'identifiant de l'utilisateur de la réponse
        const userId = res.body.id;
        console.log('Identifiant de l\'utilisateur:', userId);

        // Redirection ou autres actions en fonction de vos besoins
        // Par exemple, rediriger vers la page d'accueil
        this.router.navigate(['/accueil']);

        return res;
      }),
      catchError((error: HttpErrorResponse) => {
        // Gérer les erreurs de la requête
        console.error('Erreur de connexion:', error.message);

        // Afficher une notification d'erreur à l'utilisateur
        this.notification.error('Erreur', 'Identifiants incorrects', { nzDuration: 5000 });

        // Retourner un Observable vide pour que le flux ne soit pas interrompu
        return of(null);
      })
    );
}
login5(username:string, password:string){
  return this.http.post(Basic_Url+"api/auth/signin"+{username, password},{observe:`response`})
  .pipe(
    map((res: HttpResponse<any>)=>{
      console.log(res.body)
      this.userstorageservice.saveUser(res.body);
      const tokenLength = res.headers.get(AUTH_HEADER)?.length;
      const bearerToken= res.headers.get(AUTH_HEADER)?.substring(7,tokenLength)
      console.log(bearerToken);
      this.userstorageservice.saveToken(bearerToken);
      return res;
    })
  );

}


}
