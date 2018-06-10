import {Component, OnInit} from '@angular/core';
import {UserSharedService} from '../user/services/user.shared.service';
import {Router} from '@angular/router';

@Component({
  selector: 'nav-root',
  templateUrl: './nav.component.html'
})
export class NavComponent implements OnInit {
  isLoggedIn = false;
  username: string;

  constructor(private userSharedService: UserSharedService,
              private router: Router) {
  }

  ngOnInit() {
    this.userSharedService.isCurrentlyLoggedIn.subscribe((response) => {
      this.isLoggedIn = response;
      this.username = sessionStorage.getItem('username');

    });
  }

  initLogout(ev) {
    ev.preventDefault();
    sessionStorage.clear();
    this.userSharedService.changeIsCurrentlyLoggedIn(false);
    this.router.navigate(['/']);
  }
}
