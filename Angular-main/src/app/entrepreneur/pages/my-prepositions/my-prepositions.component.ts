import { Component, OnInit } from '@angular/core';
import { EntrepreneurService } from '../../services/entrepreneur.service';

@Component({
  selector: 'app-my-prepositions',
  templateUrl: './my-prepositions.component.html',
  styleUrls: ['./my-prepositions.component.css']
})
export class MyPrepositionsComponent implements OnInit {

  reservationServices:any;
  constructor(private entrepreneurService: EntrepreneurService,

  ) { }

  ngOnInit(): void {
    this.getMyPrepositions();
  }

  getMyPrepositions(){
    this.entrepreneurService.getMyReservations().subscribe(
      res=>{
        this.reservationServices =res;
      }
    )
  }

}
