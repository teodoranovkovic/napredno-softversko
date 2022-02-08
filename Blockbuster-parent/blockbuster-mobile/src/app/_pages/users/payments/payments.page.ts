import { Component } from '@angular/core';
import { ViewWillEnter, ViewWillLeave } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Auth } from 'src/app/_models/auth.model';
import { Payment } from 'src/app/_models/payment.model';
import { FilterService } from 'src/app/_services/filter.service';
import { PaymentService } from 'src/app/_services/payment.service';

@Component({
  selector: 'app-payments-page',
  templateUrl: './payments.page.html',
  styleUrls: ['./payments.page.scss'],
})
export class PaymentsPage implements ViewWillEnter, ViewWillLeave {
  private subscription: Subscription;

  auth: Auth;
  payments: Payment[];
  currentAge: string = 'all';
  currentType: string = 'all';

  constructor(
    private filterService: FilterService,
    private paymentService: PaymentService
  ) {}

  ionViewWillEnter() {
    this.auth = JSON.parse(localStorage.AUTH);
    this.paymentService.getPayments(this.auth.customerId).subscribe((res) => {
      this.payments = res.data;
    });

    this.subscription = this.filterService
      .getFilterObservable()
      .subscribe(({ type, filter }) => {
        if (type === 'age') {
          this.currentAge = filter;
        } else if (type === 'type') {
          this.currentType = filter;
        }
        this.filter();
      });
  }

  filter() {
    this.paymentService
      .getFilteredPayments(
        this.auth.customerId,
        this.currentAge,
        this.currentType
      )
      .subscribe((res) => {
        this.payments = res.data;
      });
  }

  ionViewWillLeave() {
    this.subscription.unsubscribe();
  }

  get typeFilter() {
    return this.filterService.typeFilter;
  }

  get ageFilter() {
    return this.filterService.ageFilter;
  }
}
