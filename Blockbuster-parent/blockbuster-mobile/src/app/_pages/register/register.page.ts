import { Component, ViewChild } from '@angular/core';
import { ViewDidLeave } from '@ionic/angular';
import { RegisterComponent } from 'src/app/_features/register/register.component';

@Component({
  selector: 'page-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements ViewDidLeave {
  @ViewChild('reg', { static: true }) reg: RegisterComponent;
  ionViewDidLeave(): void {
    this.reg.customer.reset();
  }
}
