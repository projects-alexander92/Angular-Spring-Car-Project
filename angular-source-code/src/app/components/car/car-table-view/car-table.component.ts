import {Component, OnInit} from '@angular/core';
import {CarTableService} from './car-table.service';
import {ICarTableViewModel} from './car-table-view-model.interface';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  templateUrl: './car-table.component.html',
  providers: [CarTableService],
  styleUrls: ['./car-table.component.css']
})
export class CarTableComponent implements OnInit {

  carsArray: ICarTableViewModel[];
  currentUser = sessionStorage.getItem('username');
  searchForm: FormGroup;

  constructor(private carTableService: CarTableService, private formBuilder: FormBuilder) {
    this.searchForm = this.formBuilder.group({
      make: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.carTableService.getAllCars(null).subscribe((resp) => {
      this.carsArray = resp;
      console.log(this.carsArray);
    }, err => {
      console.log(err);
    });
  }

  searchByMake(value) {
    this.carTableService.getAllCars(value.make).subscribe((resp) => {
      this.carsArray = resp;
    });
  }

  checkIfEmpty(event) {
    const value = event.target.value;
    console.log(value);
    if (value === '' || value === null) {
      this.carTableService.getAllCars(null).subscribe((resp) => {
        this.carsArray = resp;
        console.log(this.carsArray);
      });
    }
  }
}
