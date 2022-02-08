import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PaymentsPageRoutingModule } from './payments-routing.module';

import { PaymentsPage } from './payments.page';
import { SectionsModule } from 'src/app/_features/sections/sections.module';
import { SharedModule } from 'src/app/_shared/shared.module';
import { ProfileModule } from 'src/app/_features/profile/profile.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PaymentsPageRoutingModule,
    SectionsModule,
    SharedModule,
    ProfileModule,
  ],
  declarations: [PaymentsPage],
})
export class PaymentsPageModule {}
