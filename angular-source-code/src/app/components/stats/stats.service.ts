import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class StatsService {

  constructor(private httpClient: HttpClient) {
  }

  getStats(): Observable<Array<number>> {
    return this.httpClient.get<Array<number>>('http://localhost:8080/stats');
  }
}
