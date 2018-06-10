import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ICarDetails} from './car-details.interface';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class CarDetailsService {

  constructor(private httpClient: HttpClient) {
  }

  getCarById(id: number): Observable<ICarDetails> {
    return this.httpClient.get<ICarDetails>('http://localhost:8080/cars/details/' + id);
  }

  addRating(rating, carId) {
    const ratingModel = {rating: rating.rating, carId: carId};
    return this.httpClient.post('http://localhost:8080/rating/add', ratingModel);
  }

  getAverageRating(cardId) {
    return this.httpClient.get('http://localhost:8080/rating/average/' + cardId);
  }

  checkIfUserVoted(carId) {
    return this.httpClient.get('http://localhost:8080/rating/check-for-user/' + carId);
  }
}
