import { Component, OnInit, ViewChild } from '@angular/core';
import { ViewWillEnter } from '@ionic/angular';
import { take } from 'rxjs/operators';
import { RentalRequestsComponent } from 'src/app/_features/rental/rental-requests/rental-requests.component';
import { Auth } from 'src/app/_models/auth.model';
import { RentalRequest } from 'src/app/_models/rental-request.model';
import { RealTimeService } from 'src/app/_services/real-time.service';
import { RentalService } from 'src/app/_services/rental.service';

@Component({
  selector: 'app-rental-requests-page',
  templateUrl: './rental-requests.page.html',
  styleUrls: ['./rental-requests.page.scss'],
})
export class RentalRequestsPage implements OnInit, ViewWillEnter {
  // @ViewChild('requests', { static: true }) requestsComponent: RentalRequestsComponent;
  auth: Auth;
  rentalRequests: RentalRequest[];
  statusFilter: string = 'pending';
  constructor(
    private rentalService: RentalService,
    private realTime: RealTimeService
  ) {}

  ionViewWillEnter(): void {
    this.auth = JSON.parse(localStorage.AUTH);
    this.rentalService
      .getRequests(this.auth.customerId, this.statusFilter)
      .pipe(take(1))
      .subscribe((res) => {
        this.rentalRequests = res.data;
      });

    this.realTime.getNotificationsObservable().subscribe((notification) => {
      this.rentalService
        .getRequests(this.auth.customerId, this.statusFilter)
        .pipe(take(1))
        .subscribe((res) => {
          this.rentalRequests = res.data;
        });
    });
  }

  ngOnInit() {}

  segmentChanged(event) {
    this.rentalService
      .getRequests(this.auth.customerId, event.target.value)
      .pipe(take(1))
      .subscribe((res) => {
        this.statusFilter = event.target.value;
        this.rentalRequests = res.data;
      });
  }
}
