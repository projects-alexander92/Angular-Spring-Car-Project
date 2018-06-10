import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class CarAddService {

  constructor(private httpClient: HttpClient) {
  }

  submitCar(carModel) {
    return this.httpClient.post('http://localhost:8080/cars/add', carModel);
  }
}
