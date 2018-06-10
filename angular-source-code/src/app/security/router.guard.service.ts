import {Injectable} from '@angular/core';

@Injectable()
export class RouterGuardService {

  constructor() {
  }

  isUserLoggedIn(): boolean {
    return sessionStorage.getItem('userToken') !== null;
  }
}
