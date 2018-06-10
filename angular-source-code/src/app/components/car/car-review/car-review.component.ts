import {Component} from '@angular/core';
import {CarReviewService} from './car-review.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  templateUrl: './car-review.component.html',
  providers: [CarReviewService]
})
export class CarReviewComponent {
  carId: number;
  reviewForm: FormGroup;

  constructor(private activatedRoute: ActivatedRoute,
              private formBuilder: FormBuilder,
              private carReviewService: CarReviewService,
              private router: Router) {
    this.activatedRoute.params.subscribe((params) => {
      this.carId = +params['id'];
    });
    this.reviewForm = this.formBuilder.group({
      data: [null, Validators.compose([Validators.required, Validators.pattern('^[\\w ]{10,}$')])]
    });
  }

  submitReview(value) {
    const reviewBindingModel = value;
    reviewBindingModel.carId = this.carId;
    this.carReviewService.addReview(reviewBindingModel).subscribe((resp) => {
      this.router.navigate(['/cars-details/' + this.carId]);
      console.log(resp);
    });
  }
}
