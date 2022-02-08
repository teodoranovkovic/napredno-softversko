import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FilmsCategoriesPageRoutingModule } from './films-categories-routing.module';

import { FilmsCategoriesPage } from './films-categories.page';
import { SharedModule } from 'src/app/_shared/shared.module';
import { SectionsModule } from 'src/app/_features/sections/sections.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FilmsCategoriesPageRoutingModule,
    SharedModule,
    SectionsModule
  ],
  declarations: [FilmsCategoriesPage]
})
export class FilmsCategoriesPageModule {

}
