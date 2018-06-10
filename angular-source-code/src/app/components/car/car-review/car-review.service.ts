import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class CarReviewService {

  constructor(private httpClient: HttpClient) {
  }

  addReview(review) {
    return this.httpClient.post('http://localhost:8080/reviews/add', review);
  }
}
