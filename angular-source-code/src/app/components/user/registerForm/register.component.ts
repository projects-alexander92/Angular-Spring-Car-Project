import {Component} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../services/user.service';
import {Router} from '@angular/router';

@Component({
  templateUrl: './register.component.html',
  providers: [UserService],
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registrationForm: FormGroup;
  userAlreadyExists = false;
  usernameError = 'Username must be at least 5 symbols';
  passwordError = 'Password must be between 5 an 10 symbols';
  repeatPasswordError = 'Passwords do not match';


  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private router: Router) {

    const passwordValidators = Validators.compose([Validators.required, Validators.pattern('[\\w]{5,10}')]);
    this.registrationForm = this.formBuilder.group({
        username: [null, Validators.compose([Validators.required, Validators.minLength(5)])],
        password: [null, passwordValidators],
        repeatPassword: [null, passwordValidators]
      },
      {validator: RegisterComponent.matchPassword});
  }

  static matchPassword(AC: AbstractControl) {
    const password = AC.get('password').value;
    const repeatPassword = AC.get('repeatPassword').value;
    const dirty = AC.get('repeatPassword').dirty;
    if ((password !== repeatPassword) && dirty) {
      AC.get('repeatPassword').setErrors({MatchPassword: true});
    }
  }

  registerUser(formGroup) {
    const registrationModel = formGroup.value;
    this.userService.registerUser(registrationModel).subscribe((response) => {
      if (response.status !== 205) {
        this.router.navigate(['/login']);
      } else {
        this.userAlreadyExists = true;
      }
    });
  }
}

