import { Component, OnInit } from '@angular/core';
import { CreateurService } from '../../services/createur.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.css']
})
export class UpdateProjectComponent implements OnInit {

  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null ;
  validateForm!: FormGroup ;
  existingImage: string | null =null;
  imgChanged=false;

  constructor(
    private fb: FormBuilder,
    private notification :NzNotificationService,
    private router: Router,
    private createurService: CreateurService,
    private activatedroute : ActivatedRoute

  ) { }

  ngOnInit(): void {
    this.validateForm=this.fb.group({
      projectName:[null, [Validators.required]],
      description:[null, [Validators.required]],
     // projectName:[null, [Validators.required]],
    })
    this.getProjectById();

  }
  OnFileSelected(event:any){
    this.selectedFile=event.target.files[0];
    this.previewImage();
    this.existingImage=null;
    this.imgChanged=true;
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
  updateProject(){
    if (!this.selectedFile) {
      this.notification.error(
        'Error',
        'Please select a file before posting the project.',
        { nzDuration: 5000 }
      );
      return;}
    const formData : FormData= new FormData();

    if(this.imgChanged && this.selectedFile){
      formData.append('img',this.selectedFile);
    }  
    formData.append('img',this.selectedFile);
    formData.append('projectName',this.validateForm?.get('projectName')?.value);
    formData.append('description',this.validateForm?.get('description')?.value);
    this.createurService.updateProject(this.projectId,formData).subscribe(res =>{
      this.notification.success(
        'SUCCESS',
        'Project Updated successfully !',
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

  /////
  projectId = this.activatedroute.snapshot.params['id'];


  getProjectById(){
    this.createurService.getProjectById(this.projectId).subscribe(res =>{
      console.log(res);
      this.validateForm.patchValue(res);
      this.existingImage='data:image/jpeg;base64,'+res.returnedImg;
    })
  }

}
