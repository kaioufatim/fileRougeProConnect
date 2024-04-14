import { Component, OnInit } from '@angular/core';
import { CreateurService } from '../../services/createur.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-all-projects',
  templateUrl: './all-projects.component.html',
  styleUrls: ['./all-projects.component.css']
})
export class AllProjectsComponent implements OnInit {
  projects:any;

  constructor(private createurService: CreateurService,
    private notification: NzNotificationService,
    
  ) { }

  ngOnInit(): void {
    this.getAllProjectsByUserId();
  }
  getAllProjectsByUserId(){
    this.createurService.getAllProjectsByUserId().subscribe(res =>{
      this.projects=res;
    })
  }
  updateImg(img){
    return 'data:image/jpeg;base64,'+img;
  }
  
  deleteProject(projectId:any){
    this.createurService.deleteProject(projectId).subscribe(
      res=>{
        this.notification.success(
          'Success',
          'Project deleted',
          {nzDuration:5000}
        );
        this.getAllProjectsByUserId();
      }
    )
  }


}
