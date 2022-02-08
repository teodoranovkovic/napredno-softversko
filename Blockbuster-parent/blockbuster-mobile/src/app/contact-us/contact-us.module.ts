import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ContactUsPageRoutingModule } from './contact-us-routing.module';

import { ContactUsPage } from './contact-us.page';
import { SectionsModule } from '../_features/sections/sections.module';
import { SharedModule } from '../_shared/shared.module';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ContactUsPageRoutingModule,
    SectionsModule,
    SharedModule,
    RouterModule,
  ],
  declarations: [ContactUsPage]
})
export class ContactUsPageModule { }
