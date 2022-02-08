import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RentalRequestsPage } from './rental-requests.page';

const routes: Routes = [
  {
    path: '',
    component: RentalRequestsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RentalRequestsPageRoutingModule {}
