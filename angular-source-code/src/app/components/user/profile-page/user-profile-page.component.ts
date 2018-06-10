import {Component, OnInit} from '@angular/core';
import {UserProfilePageService} from './user-profile-page.service';
import {ActivatedRoute} from '@angular/router';
import {ICar} from './car-interface';

@Component({
  templateUrl: './user-profile-page.component.html',
  providers: [UserProfilePageService]
})
export class UserProfilePageComponent implements OnInit {
  username: string;
  carsArray: Array<ICar>;

  constructor(private userProfilePageService: UserProfilePageService, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe((params) => {
      this.username = params['username'];
    });
  }

  ngOnInit(): void {
    this.userProfilePageService.getUserCars().subscribe((resp) => {
      this.carsArray = resp;
    });
  }

  deleteCar(carId) {
    this.userProfilePageService.deleteCar(carId).subscribe(() => {
      this.carsArray = this.carsArray.filter((e) => {
        return e.id !== carId;
      });
    });
  }
}
