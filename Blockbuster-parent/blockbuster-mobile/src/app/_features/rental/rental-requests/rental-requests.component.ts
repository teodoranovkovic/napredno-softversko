import { Component, Input, OnInit } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { take } from 'rxjs/operators';
import { RentalRequest } from 'src/app/_models/rental-request.model';
import { RentalService } from 'src/app/_services/rental.service';

@Component({
  selector: 'app-rental-requests',
  templateUrl: './rental-requests.component.html',
  styleUrls: ['./rental-requests.component.scss'],
})
export class RentalRequestsComponent implements OnInit {
  @Input() rentalRequests: RentalRequest[];

  constructor(
    private rentalService: RentalService,
    private toast: ToastController
  ) {}

  ngOnInit() {}

  cancelRequest(request: RentalRequest) {
    this.rentalService
      .cancelRequest(request.rentalRequestId)
      .pipe(take(1))
      .subscribe(
        (res) => {
          request.status = 'canceled';
          this.toast
            .create({
              message: `${request.title} canceled successfully`,
              duration: 2000,
            })
            .then((t) => t.present());
        },
        (err) => {
          if (err.error.message) {
            this.toast
              .create({
                header: 'Error',
                message: err.error.message,
                duration: 3000,
              })
              .then((t) => t.present());
          } else {
            this.toast
              .create({
                header: 'Error',
                message: 'Server not responding',
                duration: 2000,
              })
              .then((t) => t.present());
          }
        }
      );
  }
}
