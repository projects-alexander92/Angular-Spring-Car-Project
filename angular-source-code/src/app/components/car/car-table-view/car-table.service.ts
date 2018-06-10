import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {ICarTableViewModel} from './car-table-view-model.interface';

@Injectable()
export class CarTableService {

  constructor(private httpClient: HttpClient) {
  }

  getAllCars(make: string): Observable<ICarTableViewModel[]> {
    let queryString = '';
    if (make !== null) {
      queryString = '?make=' + make;
    }
    return this.httpClient.get<ICarTableViewModel[]>('http://localhost:8080/cars/all' + queryString);
  }
}
