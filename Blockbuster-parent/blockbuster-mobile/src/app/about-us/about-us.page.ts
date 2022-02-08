import { Component, OnInit } from '@angular/core';
import { Owner } from '../_models/Owners/owner';
import { Store } from '../_models/store.model';
import { StoresPage } from '../_pages/stores/stores.page';
import { OwnerService } from '../_services/owner.service';
import { StoreService } from '../_services/store.service';


@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.page.html',
  styleUrls: ['./about-us.page.scss'],
})
export class AboutUsPage implements OnInit {


  comp;

  stors: Store[];
  constructor(private storeService: StoreService) { }

  ngOnInit() {
    this.comp = "blockbuster";

    this.storeService.getStores()
      .subscribe(res => this.stors = res);

  }
  segmentChanged($event) {
    this.comp = $event.target.value;

  }


}
