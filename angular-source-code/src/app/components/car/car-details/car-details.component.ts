import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CarDetailsService} from './car-details.service';
import {ICarDetails} from './car-details.interface';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  templateUrl: './car-details.component.html',
  providers: [CarDetailsService],
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit {
  carId: number;
  averageRating: number;
  userRating: number;
  currentUser = sessionStorage.getItem('username');
  ratingForm: FormGroup;
  car: ICarDetails;

  constructor(private activeRoute: ActivatedRoute,
              private carDetailsService: CarDetailsService,
              private formBuilder: FormBuilder) {
    // Get the id of the current car
    this.activeRoute.params.subscribe((params) => {
      this.carId = +params['id'];
    });
    // Create a form for the rating
    this.ratingForm = this.formBuilder.group({
      rating: [null, Validators.required]
    });
  }

  // Get the care details on init
  ngOnInit(): void {
    this.carDetailsService.getCarById(this.carId).subscribe((resp) => {
      this.car = resp;
    });
    this.carDetailsService.getAverageRating(this.carId).subscribe((resp: number) => {
      this.averageRating = resp;
    });
    this.carDetailsService.checkIfUserVoted(this.carId).subscribe((resp: number) => {
      this.userRating = resp;
    });
  }

  // Submit rating
  submitRating(value) {
    this.carDetailsService.addRating(value, this.carId).subscribe(() => {
      this.userRating = value.rating;
      this.carDetailsService.getAverageRating(this.carId).subscribe((resp1: number) => {
        this.averageRating = resp1;
      });
    });
  }
}
