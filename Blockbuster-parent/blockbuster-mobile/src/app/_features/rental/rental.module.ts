import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RentalRequestsComponent } from './rental-requests/rental-requests.component';
import { IonicModule } from '@ionic/angular';
import { SharedModule } from 'src/app/_shared/shared.module';

@NgModule({
  declarations: [RentalRequestsComponent],
  imports: [CommonModule, IonicModule, SharedModule],
  exports: [RentalRequestsComponent],
})
export class RentalModule {}
