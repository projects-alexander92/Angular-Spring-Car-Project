import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from '../components/user/loginForm/login.component';
import {RegisterComponent} from '../components/user/registerForm/register.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {RouterGuard} from '../security/router.guard';
import {RouterGuardService} from '../security/router.guard.service';
import {CustomHttpInterceptor} from '../interceptors/http.interceptor';
import {CarAddComponent} from '../components/car/car-add/car-add.component';
import {CarTableComponent} from '../components/car/car-table-view/car-table.component';
import {CarDetailsComponent} from '../components/car/car-details/car-details.component';
import {CarReviewComponent} from '../components/car/car-review/car-review.component';
import {UserProfilePageComponent} from '../components/user/profile-page/user-profile-page.component';
import {HomePageComponent} from '../components/home-page/home-page.component';
import {StatsComponent} from '../components/stats/stats.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'stats', component: StatsComponent},
  {path: '', component: HomePageComponent},
  {path: 'cars-add', component: CarAddComponent, canActivate: [RouterGuard]},
  {path: 'cars-all', component: CarTableComponent, canActivate: [RouterGuard]},
  {path: 'cars-details/:id', component: CarDetailsComponent, canActivate: [RouterGuard]},
  {path: 'add-review/:id', component: CarReviewComponent, canActivate: [RouterGuard]},
  {path: 'user-profile/:username', component: UserProfilePageComponent, canActivate: [RouterGuard]}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    BrowserModule,
    HttpClientModule
  ],
  providers: [RouterGuard, RouterGuardService, {provide: HTTP_INTERCEPTORS, useClass: CustomHttpInterceptor, multi: true}],
  declarations: [
    LoginComponent,
    RegisterComponent,
    CarAddComponent,
    CarTableComponent,
    CarDetailsComponent,
    CarReviewComponent,
    UserProfilePageComponent,
    HomePageComponent,
    StatsComponent
  ],
  exports: [RouterModule]
})

export class AppRoutsModule {

}
