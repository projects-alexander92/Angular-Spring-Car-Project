import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {RouterGuardService} from './router.guard.service';

@Injectable()
export class RouterGuard implements CanActivate {

  constructor(private routerGuardService: RouterGuardService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.routerGuardService.isUserLoggedIn()) {
      this.router.navigate(['/login']);
    } else {
      return this.routerGuardService.isUserLoggedIn();
    }
  }

}
