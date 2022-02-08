import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import * as moment from 'moment';
import { throwError } from 'rxjs';
import { catchError, take } from 'rxjs/operators';
import { Auth } from 'src/app/_models/auth.model';
import { ErrorResponse } from 'src/app/_models/http/error-responser.model';
import { CustomerService } from 'src/app/_services/customer.service';
import { PaymentService } from 'src/app/_services/payment.service';

@Component({
  selector: 'app-add-money-modal',
  templateUrl: './add-money-modal.component.html',
  styleUrls: ['./add-money-modal.component.scss'],
})
export class AddMoneyModalComponent implements OnInit {
  validation_messages = {
    money: [
      { type: 'required', message: 'Money to add is required' },
      {
        type: 'min',
        message: 'You must add at least 0.01$',
      },
    ],
  };

  @Input() balance: number;
  form: FormGroup;
  error: string = undefined;
  constructor(
    private modalController: ModalController,
    private customerService: CustomerService
  ) {}

  ngOnInit() {
    this.form = new FormGroup({
      money: new FormControl(0.01, {
        validators: [Validators.min(0.01), Validators.required],
      }),
    });
  }

  get money() {
    return this.form.get('money');
  }

  async dismiss() {
    await this.modalController.dismiss(0);
  }

  async addMoney(event: Event) {
    event.preventDefault();
    if (this.form.valid) {
      const auth: Auth = JSON.parse(localStorage.AUTH);
      this.customerService
        .addMoney(auth.customerId, this.form.get('money').value)
        .pipe(take(1))
        .subscribe(
          async (res) => {
            const money = Number(this.form.get('money').value);
            auth.balance += money;
            localStorage.setItem('AUTH', JSON.stringify(auth));
            this.balance = auth.balance;
            await this.modalController.dismiss(money);
          },
          async (err: ErrorResponse) => {
            console.log(err.error.message);
          }
        );
    }
  }
}
