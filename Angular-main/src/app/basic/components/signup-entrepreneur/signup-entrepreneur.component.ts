import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-signup-entrepreneur',
  templateUrl: './signup-entrepreneur.component.html',
  styleUrls: ['./signup-entrepreneur.component.css']
})
export class SignupEntrepreneurComponent implements OnInit {

  validateForm !: FormGroup;

  constructor( private fb:FormBuilder,
    private authService: AuthService,
    private notification: NzNotificationService,
    private router: Router
    ) { }

    ngOnInit(): void {
      this.validateForm = this.fb.group({
         email: [null, [Validators.email, Validators.required]],
         name: [null, [Validators.required]],
         lastname: [null, [Validators.required]],
         phone: [null],
         password: [null, [Validators.required]],
         checkPassword: [null, [Validators.required]],
      });
     }

     submitForm(){
      this.authService.registerEntrepreneur(this.validateForm.value).subscribe(res =>{
        this.notification.success(
          'SUCCESS',
          'Signup successful',
          {nzDuration: 5000}
        );
        this.router.navigateByUrl('/login');
      } , error =>{
        this.notification.error(
          'Error',
          `${error.error}`,
          {nzDuration:5000}
        )
      })
     }
     
  

}
