import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ICar} from './car-interface';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class UserProfilePageService {

  constructor(private httpclient: HttpClient) {
  }

  getUserInfo(userId) {
    return 12;
  }

  deleteCar(carId) {
    return this.httpclient.delete('http://localhost:8080/cars/delete/' + carId);
  }

  getUserCars(): Observable<Array<ICar>> {
    return this.httpclient.get<Array<ICar>>('http://localhost:8080/cars/all/by-username');
  }
}
