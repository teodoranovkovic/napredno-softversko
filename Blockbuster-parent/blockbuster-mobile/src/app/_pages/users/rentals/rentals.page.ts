import { Component, OnInit } from '@angular/core';
import { ViewWillEnter } from '@ionic/angular';
import * as moment from 'moment';
import { map, take } from 'rxjs/operators';
import { Auth } from 'src/app/_models/auth.model';
import { RentalListItem } from 'src/app/_models/rental-list-item.model';
import { RealTimeService } from 'src/app/_services/real-time.service';
import { RentalService } from 'src/app/_services/rental.service';
import { Notification } from 'src/app/_models/notification.model';

@Component({
  selector: 'app-rentals',
  templateUrl: './rentals.page.html',
  styleUrls: ['./rentals.page.scss'],
})
export class RentalsPage implements ViewWillEnter {
  auth: Auth;
  rentals: RentalListItem[] = [];
  filteredRentals: RentalListItem[] = [];
  ageFilter: 'today' | 'week' | 'month' | 'all' | undefined = undefined;
  statusFilter: 'all' | 'returned' | 'notReturned' = undefined;

  constructor(
    private rentalService: RentalService,
    private realTime: RealTimeService
  ) { }

  ionViewWillEnter() {
    this.auth = JSON.parse(localStorage.AUTH);
    if (this.auth) {
      this.rentalService
        .getRentals(this.auth.customerId)
        .pipe(take(1))
        .subscribe((res) => {
          this.rentals = res.data;
          this.filteredRentals = res.data;
        });
      this.realTime.getNotificationsObservable().subscribe((notification) => {
        if (notification.customerId === this.auth.customerId) {
          this.rentalService
            .getRentals(this.auth.customerId)
            .pipe(take(1))
            .subscribe((res) => {
              this.rentals = res.data;
              this.filter();
            });
        }
      });
    }
  }

  filter() {
    this.filteredRentals = this.rentals.filter((rental) => {
      let gucci = true;
      if (this.ageFilter) {
        gucci = this.filterByAge(rental);
        if (gucci === false) return false;
      }
      if (this.statusFilter) {
        gucci = this.filterByStatus(rental);
        if (gucci === false) return false;
      }
      return true;
    });
  }
  filterByStatus(rental: RentalListItem): boolean {
    if (this.statusFilter === 'all') return true;
    else if (this.statusFilter === 'returned' && rental.returnDate) return true;
    else if (this.statusFilter === 'notReturned' && !rental.returnDate)
      return true;
    return false;
  }

  filterByAge(rental: RentalListItem) {
    const date = new Date(rental.rentalDate);
    if (!date) return false;
    const now = new Date(Date.now());
    if (this.ageFilter === 'today') {
      if (moment(date).isBetween(moment(now).subtract(1, 'day'), now)) {
        return true;
      }
    } else if (this.ageFilter === 'week') {
      if (moment(date).isBetween(moment(now).subtract(7, 'day'), now)) {
        return true;
      }
    } else if (this.ageFilter === 'month') {
      if (moment(date).isBetween(moment(now).subtract(1, 'month'), now)) {
        return true;
      }
    } else if (this.ageFilter === 'all') {
      return true;
    }

    return false;
  }

  segmentChanged(event) {
    this.ageFilter = event.target.value;
    this.filter();
  }

  statusChanged(event) {
    this.statusFilter = event.target.value;
    this.filter();
  }
}
