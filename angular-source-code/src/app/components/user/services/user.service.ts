import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserModel, LoginResponse} from '../interfaces/login.model.interface';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class UserService {

  constructor(private httpClient: HttpClient) {
  }

  registerUser(registrationModel: object) {
    return this.httpClient.post('http://localhost:8080/register', registrationModel,
      {observe: 'response', responseType: 'text'});

  }

  loginUser(registrationModel: UserModel): Observable<LoginResponse> {
    const userModel = {
      username: registrationModel.username,
      password: registrationModel.password
    };
    return this.httpClient.post<LoginResponse>('http://localhost:8080/authenticate', userModel);
  }
}
