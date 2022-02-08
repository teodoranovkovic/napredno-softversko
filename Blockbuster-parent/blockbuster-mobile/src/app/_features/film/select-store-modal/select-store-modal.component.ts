import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  AlertController,
  ModalController,
  PopoverController,
  ToastController,
} from '@ionic/angular';
import { take } from 'rxjs/operators';
import { Auth } from 'src/app/_models/auth.model';
import { FilmDetails } from 'src/app/_models/film-details.model';
import { StoreWithInventory } from 'src/app/_models/store-with-inventory.model';
import { Store } from 'src/app/_models/store.model';
import { InventoryService } from 'src/app/_services/inventory.service';
import { RentalService } from 'src/app/_services/rental.service';

@Component({
  selector: 'app-select-store-modal',
  templateUrl: './select-store-modal.component.html',
  styleUrls: ['./select-store-modal.component.scss'],
})
export class SelectStoreModalComponent implements OnInit {
  @Input() film: FilmDetails;
  stores: StoreWithInventory[];

  constructor(
    private inventoryService: InventoryService,
    private rentalService: RentalService,
    private router: Router,
    private toast: ToastController,
    private alert: AlertController,
    private popoverController: PopoverController
  ) {}

  ngOnInit() {
    const auth: Auth = JSON.parse(localStorage.AUTH);
    this.inventoryService
      .getStoresWithInventory(this.film.filmId)
      .pipe(take(1))
      .subscribe((res) => {
        this.stores = res.data
          .filter((s) => s.inventoryCount > 0)
          .sort((a, b) => {
            if (auth.storeId === b.storeId) return 1;
            return 0;
          });
      });
  }

  dismiss() {
    this.popoverController.dismiss();
  }

  rent(store: StoreWithInventory) {
    this.alert
      .create({
        header: 'Rent film',
        message: 'Are you sure you want to rent this film?',
        buttons: [
          {
            text: "I'm sure",
            handler: () => {
              this.rentalService
                .rentFilm(this.film.filmId, store.storeId)
                .pipe(take(1))
                .subscribe(
                  (res) => {
                    this.toast
                      .create({
                        message: `You will be fined ${(
                          this.film.rentalDuration * this.film.rentalRate
                        ).toFixed()}$ after request confirmation`,
                        header: `Sent request for ${this.film.title} successfully`,
                        duration: 5000,
                      })
                      .then((t) => t.present());
                    this.popoverController.dismiss();
                    this.router.navigate(['home']);
                  },
                  (err) => {
                    this.popoverController.dismiss();
                    if (err.error.message) {
                      this.toast
                        .create({
                          header: 'Error',
                          message: err.error.message,
                          duration: 3000,
                        })
                        .then((t) => t.present());
                    } else {
                      this.toast
                        .create({
                          header: 'Error',
                          message: 'Server not responding',
                          duration: 2000,
                        })
                        .then((t) => t.present());
                    }
                  }
                );
            },
          },
          { text: 'Take me back' },
        ],
      })
      .then((a) => a.present());
  }
}
