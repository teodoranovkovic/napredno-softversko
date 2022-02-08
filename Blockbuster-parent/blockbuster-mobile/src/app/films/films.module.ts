import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilmsComponent } from './films.component';
import { SharedModule } from '../_shared/shared.module';
import { IonicModule } from '@ionic/angular';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    IonicModule
  ],
  declarations: [
    FilmsComponent
  ],
  exports: [
    FilmsComponent
  ]

})
export class FilmsModule { }
