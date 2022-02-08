import { nullSafeIsEquivalent } from '@angular/compiler/src/output/output_ast';
import { Injectable } from '@angular/core';
import { Owner } from '../_models/Owners/owner';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  constructor() { }


  owners: Owner[] = [

    {
      firstName: 'Teodora',
      lastName: 'Novkovic',
      email: 'teodora.novkovic@dualsoft.com',
      imageUrl: 'https://i.ibb.co/QCGr5J5/TEODORA-JA.jpg',
      instaLink: 'https://www.instagram.com/t_novkovic/',
      fbLink: 'https://www.facebook.com/teodora.novkovic.7/',
      inLink: 'https://www.linkedin.com/in/teodora-novkovic-729654135/',
      school: 'Elekentronski fakultet',
      city: 'Nis',
      phone: '+381 64 27 415 14'
    }

  ];



  getAllOwners() {
    return [...this.owners];
  }

}
