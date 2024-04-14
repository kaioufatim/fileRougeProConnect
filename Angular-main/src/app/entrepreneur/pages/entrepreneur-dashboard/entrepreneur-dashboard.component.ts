import { Component, OnInit } from '@angular/core';
import { EntrepreneurService } from '../../services/entrepreneur.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-entrepreneur-dashboard',
  templateUrl: './entrepreneur-dashboard.component.html',
  styleUrls: ['./entrepreneur-dashboard.component.css']
})
export class EntrepreneurDashboardComponent implements OnInit {

  projects:any=[];
  validateForm!: FormGroup;

  constructor(private entrepreneurService:EntrepreneurService,
    private fb: FormBuilder,

  ) { }
  getAllProject(){
    this.entrepreneurService.getAllProjects().subscribe(res =>{
      this.projects=res;
    })
  }
  

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      service : [null,[Validators.required]]
    })
    this.getAllProject();
  }
  searchProjectByName(){
    this.entrepreneurService.searchProjectByName(this.validateForm.get(['service']).value).subscribe(
      res =>{
        this.projects=res;
      })
  }



  updateImg(img){
    return 'data:image/jpeg;base64,'+img;
  }
}
