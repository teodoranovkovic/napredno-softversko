import { Component, OnInit } from '@angular/core';
import { Owner } from '../_models/Owners/owner';
import { OwnerService } from '../_services/owner.service';

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.page.html',
  styleUrls: ['./contact-us.page.scss'],
})
export class ContactUsPage implements OnInit {

  owners: Owner[];

  constructor(private ownerService: OwnerService) { }

  ngOnInit() {
    this.owners = this.ownerService.getAllOwners();
  }


}
