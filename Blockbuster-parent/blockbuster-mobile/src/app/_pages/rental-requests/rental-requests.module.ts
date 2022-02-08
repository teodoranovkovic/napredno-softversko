import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { RentalRequestsPageRoutingModule } from './rental-requests-routing.module';

import { RentalRequestsPage } from './rental-requests.page';
import { SectionsModule } from 'src/app/_features/sections/sections.module';
import { RentalModule } from 'src/app/_features/rental/rental.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RentalRequestsPageRoutingModule,
    SectionsModule,
    RentalModule,
  ],
  declarations: [RentalRequestsPage],
})
export class RentalRequestsPageModule {}
