import {Component} from '@angular/core';
import {CarAddService} from './car-add.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  templateUrl: './car-add.component.html',
  providers: [CarAddService],
  styleUrls: ['./car-add.css']
})
export class CarAddComponent {
  carForm: FormGroup;
  currentYear = new Date().getFullYear();
  defaultPirceValue = 0;

  constructor(private formBuilder: FormBuilder,
              private carService: CarAddService,
              private router: Router) {
    this.carForm = this.formBuilder.group({
      make: [null, Validators.compose([Validators.required])],
      model: [null, Validators.compose([Validators.required])],
      year: [null, Validators.compose([Validators.min(1980), Validators.max(this.currentYear)])],
      engine: [null, Validators.compose([Validators.required])],
      price: [null, Validators.compose([Validators.required, Validators.min(1)])],
      imageUrl: [null, Validators.compose([Validators.required, Validators.pattern(/^http.+$/)])],
      mileage: [null, Validators.compose([Validators.required, Validators.min(1)])]
    });
  }

  submitCarForm(form) {
    this.carService.submitCar(form).subscribe((e) => {
      console.log(e);
      this.router.navigate(['/cars-all']);
    }, err => {
      console.log(err);
    });
    console.log(form);
  }
}
