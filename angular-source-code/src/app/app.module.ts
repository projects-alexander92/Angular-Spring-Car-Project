import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {NavComponent} from './components/navbar/navbar.component';
import {AppRoutsModule} from './router/routes.module';
import {UserSharedService} from './components/user/services/user.shared.service';


@NgModule({
  declarations: [
    AppComponent, NavComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutsModule
  ],
  providers: [UserSharedService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
