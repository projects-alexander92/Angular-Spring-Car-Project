import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {InterceptorToast} from './interceptor.toast';

export class CustomHttpInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = sessionStorage.getItem('userToken');
    let request = req;
    if (token !== null) {
      request = req.clone({headers: req.headers.set('Authorization', token)});
    }
    return next.handle(request).map((event: HttpEvent<any>) => {
      InterceptorToast.showToast(event);
      return event;
    })
      .catch((event: HttpErrorResponse) => {
        InterceptorToast.showToast(event);
        return Observable.throw(event);
      });

  }
}
