import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { Router } from '@angular/router';
import { UserstorageService } from '../../services/storage/userstorage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  validateForm !: FormGroup;

  constructor(private fb : FormBuilder,
    private authService: AuthService,
    private notification : NzNotificationService,
    private router : Router,

    ) { }

  ngOnInit(): void {
     this.validateForm = this.fb.group({
      userName : [null ,[Validators.required]],
      password : [null ,[Validators.required]],

     })
  }
  submitForm(){
    this.authService.login(this.validateForm.get(['userName'])!.value,this.validateForm.get(['password'])!.value)
    .subscribe(res =>{
      console.log(res)
      if(UserstorageService.isentrepreneurLoggedIn()){
        console.log('Redirecting to entrepreneur dashboard');

        this.router.navigateByUrl('entrepreneur/dashboard')
      }else if(UserstorageService.iscreateurLoggedIn()){
        console.log('Redirecting to createur dashboard');

        this.router.navigateByUrl('createur/dashboard')

      }
    }, error =>{
      this.notification.error(
        'Error',
        'Bad credentials',
        { nzDuration:5000}
      )
    }
    
    )
  }

}
