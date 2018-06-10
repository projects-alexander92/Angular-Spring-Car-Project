import * as toastr from 'toastr';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';

export class InterceptorToast {

  static showToast(event) {
    toastr.options = {
      closeButton: true,
      progressBar: true,
      preventDuplicates: true,
      timeOut: 3000,
    };
    if (event instanceof HttpResponse) {
      if (event.status === 205) {
        toastr.error(event.status + this.eventMessage(event.status));
      } else {
        toastr.success(event.status + this.eventMessage(event.status));
      }
    }
    if (event instanceof HttpErrorResponse) {
      toastr.error(event.status + this.eventMessage(event.status));
    }
  }

  static eventMessage(status: number): string {
    switch (status) {
      case 200:
        return ' Ok';
      case 205:
        return ' Username all ready exists';
      case 401:
        return ' Unauthorized';
      case 403:
        return ' Forbidden';
      case 406: return ' Not Acceptable';
      default:
        return ' Server Error';
    }
  }
}
