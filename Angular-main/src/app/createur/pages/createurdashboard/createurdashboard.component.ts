import { Component, OnInit } from '@angular/core';
import { CreateurService } from '../../services/createur.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-createurdashboard',
  templateUrl: './createurdashboard.component.html',
  styleUrls: ['./createurdashboard.component.css']
})
export class CreateurdashboardComponent implements OnInit {
  reservation :any;

  constructor(private createurService : CreateurService,
    private notification :NzNotificationService,

  ) { }

  ngOnInit(): void {
    this.getAllProjectReservation();
  }
  getAllProjectReservation(){
    this.createurService.getAllProjectReservation().subscribe(res =>{
      console.log(res);
      this.reservation = res;
    })
  }

  changeReservationStatus(reservationId:number,status:string){
    this.createurService.changeReservatinStaus(reservationId,status).subscribe(res =>{
      this.notification.success(
        'Success',
        'Proposition status Changed Successfully',
        {nzDuration:5000}
      );
      this.getAllProjectReservation();
    },error =>{
      this.notification.error(
        'Error',
       ` ${error.message}`,
       {nzDuration:5000}
      )
    }
    )
  }

}
