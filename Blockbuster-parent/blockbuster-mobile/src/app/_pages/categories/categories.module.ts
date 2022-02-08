import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CategoriesPageRoutingModule } from './categories-routing.module';

import { CategoriesPage } from './categories.page';
import { SectionsModule } from 'src/app/_features/sections/sections.module';
import { SharedModule } from 'src/app/_shared/shared.module';
import { FilmsAllPostersCategoriesComponent } from 'src/app/films-all-posters-categories/films-all-posters-categories.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CategoriesPageRoutingModule,
    SectionsModule,
    SharedModule,

  ],
  declarations: [
    CategoriesPage,
    FilmsAllPostersCategoriesComponent,
  ],
  exports: [
    FilmsAllPostersCategoriesComponent,
  ]
})
export class CategoriesPageModule { }
