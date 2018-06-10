import {Component, OnInit} from '@angular/core';
import {StatsService} from './stats.service';

@Component({
  templateUrl: './stats.component.html',
  providers: [StatsService]
})
export class StatsComponent implements OnInit {
  carCount: number;
  usersCount: number;

  constructor(private statsService: StatsService) {
  }

  ngOnInit(): void {
    this.statsService.getStats().subscribe((resp) => {
      this.carCount = resp[0];
      this.usersCount = resp[1];
    });
  }

}
