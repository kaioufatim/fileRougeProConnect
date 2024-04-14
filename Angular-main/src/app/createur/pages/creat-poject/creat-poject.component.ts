import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { CreateurService } from '../../services/createur.service';

@Component({
  selector: 'app-creat-poject',
  templateUrl: './creat-poject.component.html',
  styleUrls: ['./creat-poject.component.css']
})
export class CreatPojectComponent implements OnInit {

  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null ;
  validateForm!: FormGroup ;
  constructor(
    private fb: FormBuilder,
    private notification :NzNotificationService,
    private router: Router,
    private createurService: CreateurService,
  ) { }

  ngOnInit(): void {
    this.validateForm=this.fb.group({
      projectName:[null, [Validators.required]],
      description:[null, [Validators.required]],
     // projectName:[null, [Validators.required]],

    })
  }
  OnFileSelected(event:any){
    this.selectedFile=event.target.files[0];
    this.previewImage();
  }
  previewImage(){
    if (!this.selectedFile) {
     console.error('No file selected for preview.');
      return; 
   }
    const reader = new FileReader();
    reader.onload=()=>{
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }
  postProject(){
    if (!this.selectedFile) {
      this.notification.error(
        'Error',
        'Please select a file before posting the project.',
        { nzDuration: 5000 }
      );
      return;}
    const formData : FormData= new FormData();
    formData.append('img',this.selectedFile);
    formData.append('projectName',this.validateForm?.get('projectName')?.value);
    formData.append('description',this.validateForm?.get('description')?.value);
    this.createurService.PostProject(formData).subscribe(res =>{
      this.notification.success(
        'SUCCESS',
        'Project Posted successfully !',
        { nzDuration:5000}
      );
      this.router.navigateByUrl('/createur/projects');
    }, error =>{
      this.notification.error(
        'Error',
        `${error.error}`,
        {nzDuration : 5000}
      )
    })

  }

}
