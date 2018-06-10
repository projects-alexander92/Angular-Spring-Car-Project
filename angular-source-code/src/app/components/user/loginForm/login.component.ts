import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../services/user.service';
import {UserModel, LoginResponse} from '../interfaces/login.model.interface';
import {UserSharedService} from '../services/user.shared.service';
import {Router} from '@angular/router';

@Component({
  templateUrl: './login.component.html',
  providers: [UserService]
})
export class LoginComponent {
  loginForm: FormGroup;
  invalidCredentials = false;
  loginModel: UserModel;


  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private userSharedService: UserSharedService,
              private router: Router) {
    this.loginForm = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  loginUser(form) {
    sessionStorage.clear();
    this.loginModel = form.value;
    this.userService.loginUser(this.loginModel).subscribe((resp) => {
      sessionStorage.setItem('username', this.loginModel.username);
      sessionStorage.setItem('userToken', 'Bearer ' + resp.id_token);
      this.userSharedService.changeIsCurrentlyLoggedIn(true);
      this.router.navigate(['./']);
    }, () => {
      this.invalidCredentials = true;
    });
  }
}
