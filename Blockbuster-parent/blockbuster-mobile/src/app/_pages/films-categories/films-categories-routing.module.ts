import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FilmsCategoriesPage } from './films-categories.page';

const routes: Routes = [
  {
    path: '',
    component: FilmsCategoriesPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FilmsCategoriesPageRoutingModule {}
