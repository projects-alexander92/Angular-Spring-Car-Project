import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

let secessionStorageUser = false;
const username = sessionStorage.getItem('username');
if (username !== null) {
  secessionStorageUser = true;
}
/*Променяме login-logout state на nav component*/
@Injectable()
export class UserSharedService {
  private loggedInUser = new BehaviorSubject<boolean>(secessionStorageUser);
  isCurrentlyLoggedIn = this.loggedInUser.asObservable();

  constructor() {
  }

  changeIsCurrentlyLoggedIn(message: boolean) {
    this.loggedInUser.next(message);
  }
}
