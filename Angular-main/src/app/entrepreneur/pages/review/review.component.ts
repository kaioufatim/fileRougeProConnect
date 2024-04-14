import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { EntrepreneurComponent } from '../../entrepreneur.component';
import { EntrepreneurService } from '../../services/entrepreneur.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { UserstorageService } from 'src/app/basic/services/storage/userstorage.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {


  reservationId :number=this.activatedRoute.snapshot.params[`id`];
  validateForm!:FormGroup;

  constructor(private fb : FormBuilder,
    private notification:NzNotificationService,
    private entrepreneurService:EntrepreneurService,
    private router: Router,
    private activatedRoute: ActivatedRoute,

  ) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      rating: [null,Validators.required],
      review: [null,Validators.required],
    })
  }

  giveReview(){
    const reviewDto = {
      rating: this.validateForm.get("rating").value,
      review : this.validateForm.get("review").value,
      userId:UserstorageService.getUserId(),

      reservationId:this.reservationId,
    }
    this.entrepreneurService.giveReview(reviewDto).subscribe(res=>{
      this.notification.success('Success',
        'Review posted Successfully',
        {nzDuration:5000}
      );
      this.router.navigateByUrl("/entrepreneur/propositions")

    },
  error=>{
    this.notification.error(
      'Error',
      `${error.message}`,
      {nzDuration:5000}
    )
  })
  }


}
