import { Component, OnInit } from '@angular/core';
import { EntrepreneurService } from '../../services/entrepreneur.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { UserstorageService } from 'src/app/basic/services/storage/userstorage.service';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

  projectId = this.activateroute.snapshot.params['projectId'];
  avatarUrl : any;
  project : any;

  validateForm! :FormGroup;

  constructor(private entrepreneurService : EntrepreneurService,
    private activateroute: ActivatedRoute,
    private notification: NzNotificationService,
    private router :Router,
    private fb : FormBuilder,
  ) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      propositionDate: [null, [Validators.required]]
    })
    this.getProjectDetailsByProjectId();

  }

  getProjectDetailsByProjectId(){
    this.entrepreneurService.getProjectdetailsByProjectId(this.projectId).subscribe(res => {
      console.log(res);
      this.avatarUrl = 'data:image/jpeg;base64,'+res.projectDto.returnedImg;
      this.project= res.projectDto;
    })
  }

  propositionService(){
    const propositionServiceDto={
      propositionDate : this.validateForm.get(['propositionDate']).value,
      projectId :this.projectId,
      userId : UserstorageService.getUserId()
    }
    this.entrepreneurService.propositionService(propositionServiceDto).subscribe(res =>{
      this.notification.success(
        'Success',
        'Request posted successfully',
        {nzDuration:5000}
      );
      this.router.navigateByUrl('/entrepreneur/reservation');
    })
  }
}
