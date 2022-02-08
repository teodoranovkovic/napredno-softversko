import { Store } from './../../_models/store.model';
import { Component } from '@angular/core';
import { StoreService } from 'src/app/_services/store.service';
import { AuthService } from 'src/app/_services/auth.service';
import { Auth } from 'src/app/_models/auth.model';
import * as Leaflet from 'leaflet';
import { ToastController, ViewDidEnter, ViewWillEnter } from '@ionic/angular';
import { Geolocation } from '@ionic-native/geolocation/ngx';

import 'leaflet/dist/images/marker-shadow.png';
import 'leaflet/dist/images/marker-icon.png';
import 'leaflet/dist/images/marker-icon-2x.png';
import { CustomerService } from 'src/app/_services/customer.service';

@Component({
  selector: 'app-stores',
  templateUrl: './stores.page.html',
  styleUrls: ['./stores.page.scss'],
})
export class StoresPage implements ViewWillEnter, ViewDidEnter {
  auth: Auth = undefined;
  loggedUser: boolean = this.auth ? true : false;

  constructor(
    private storeService: StoreService,
    private authService: AuthService,
    private customerService: CustomerService,
    private geoLocation: Geolocation,
    private toastController: ToastController
  ) {}

  stores: Store[] = [];
  localStores: Store[] = [];
  focusedStore: Store;
  myStore: Store;

  map: Leaflet.Map;

  ionViewWillEnter() {
    const auth = localStorage.AUTH;
    if (auth) {
      this.auth = JSON.parse(auth);
    }

    this.authService
      .getAuthObservable()
      .subscribe((auth) => (this.auth = auth));
  }

  ionViewDidEnter() {
    if (!this.map) {
      this.map = Leaflet.map('map').setView(
        [43.31828269870036, 21.888207555637397],
        12
      );
      Leaflet.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: 'edupala.com © Angular LeafLet',
      }).addTo(this.map);

      this.storeService.getStores().subscribe((stores) => {
        this.stores = stores;
        this.stores.forEach((store) => {
          Leaflet.marker([store.latitude, store.longitude])
            .bindPopup(`${store.address}`)
            .addTo(this.map)
            .on('click', (e) => {
              this.focusedStore = store;
            });
        });
        this.geoLocation.getCurrentPosition().then((res) => {
          const { latitude, longitude } = res.coords;
          stores.forEach((store) => {
            if (store.storeId === this.auth?.storeId) {
              this.myStore = store;
            }
            if (
              this.checkInRange(
                { lat: latitude, lng: longitude },
                { lat: store.latitude, lng: store.longitude },
                20
              )
            ) {
              this.localStores.push(store);
            }
            // Leaflet.marker([store.latitude, store.longitude])
            //   .bindPopup(`${store.address}`)
            //   .addTo(this.map)
            //   .on('click', (e) => {
            //     this.focusedStore = store;
            //   });
          });
        });
      });

      this.geoLocation.getCurrentPosition().then((res) => {
        const lat = res.coords.latitude;
        const lon = res.coords.longitude;
        Leaflet.circleMarker([lat, lon])
          .bindPopup(`You are here!`)
          .addTo(this.map);
        this.map.panTo({ lat, lng: lon });
      });
    }
  }

  checkInRange(
    loc1: { lat: number; lng: number },
    loc2: { lat: number; lng: number },
    range: number
  ) {
    const x = loc1.lat - loc2.lat;
    const y = loc1.lng - loc2.lng;
    //Mult with 111.1 to convert to km
    const distance = Math.sqrt(x * x + y * y) * 111.1;
    return distance < range;
  }

  showOnMap(store: Store) {
    this.map.panTo({ lat: store.latitude, lng: store.longitude });
  }

  selectStore(store: Store) {
    this.customerService
      .selectStore(this.auth.customerId, store.storeId)
      .subscribe(async (res) => {
        this.auth.storeId = store.storeId;
        localStorage.setItem('AUTH', JSON.stringify(this.auth));
        this.myStore = store;
        const toast = await this.toastController.create({
          message: `${store.address} selected`,
          // buttons: [{ text: 'ok' }],
          duration: 3000,
          animated: true,
        });
        await toast.present();
      });
  }
}
