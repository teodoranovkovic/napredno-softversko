import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddMoneyModalComponent } from './add-money-modal/add-money-modal.component';
import { IonicModule } from '@ionic/angular';
import { ReactiveFormsModule } from '@angular/forms';
import { ProfileComponent } from './profile/profile.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'src/app/_shared/shared.module';
import { PaymentsComponent } from './payments/payments.component';

@NgModule({
  declarations: [
    AddMoneyModalComponent,
    ProfileComponent,
    ProfileComponent,
    PaymentsComponent,
  ],
  imports: [
    CommonModule,
    IonicModule,
    ReactiveFormsModule,
    RouterModule,
    SharedModule,
  ],
  exports: [
    AddMoneyModalComponent,
    ProfileComponent,
    ProfileComponent,
    PaymentsComponent,
  ],
})
export class ProfileModule {}
