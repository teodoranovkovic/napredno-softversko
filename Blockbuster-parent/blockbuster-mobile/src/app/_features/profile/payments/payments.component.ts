import { Component, Input } from '@angular/core';
import { Payment } from 'src/app/_models/payment.model';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.scss'],
})
export class PaymentsComponent {
  @Input() payments: Payment[];

  constructor() {}
}
