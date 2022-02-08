import { RouterModule } from '@angular/router';
import { SharedModule } from 'src/app/_shared/shared.module';
import { SectionsModule } from './../../_features/sections/sections.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ActorPageRoutingModule } from './actor-routing.module';

import { ActorPage } from './actor.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ActorPageRoutingModule,
    SectionsModule,
    SharedModule,
    RouterModule,
  ],
  declarations: [ActorPage]
})
export class ActorPageModule { }
