import { Component, Input, OnInit } from '@angular/core';
import { PopoverController } from '@ionic/angular';
import { take } from 'rxjs/operators';
import { Auth } from 'src/app/_models/auth.model';
import { FilmDetails } from 'src/app/_models/film-details.model';
import { RentalService } from 'src/app/_services/rental.service';
import { SelectStoreModalComponent } from '../select-store-modal/select-store-modal.component';

@Component({
  selector: 'app-film-detail',
  templateUrl: './film-detail.component.html',
  styleUrls: ['./film-detail.component.scss'],
})
export class FilmDetailComponent implements OnInit {
  @Input() auth: Auth;
  @Input() film: FilmDetails;
  hasRequested = false;



  constructor(
    private rentalService: RentalService,
    private popoverController: PopoverController
  ) {}
  ngOnInit(): void {
    if (localStorage.AUTH) {
      const auth: Auth = JSON.parse(localStorage.AUTH);
      this.rentalService
        .hasRequested(auth.customerId, this.film.filmId)
        .pipe(take(1))
        .subscribe((res) => {
          this.hasRequested = res.data;
        });
    }
  }

  openSelectStoreModal(event, film: FilmDetails) {
    if (this.auth) {
      this.popoverController
        .create({
          component: SelectStoreModalComponent,
          componentProps: {
            film: film,
            event,
          },
        })
        .then((modal) => modal.present());
    }
  }

  isFilmFavourite() {
    const customer = JSON.parse(localStorage.getItem('AUTH'));
    const customerAndFavouriteFilms = JSON.parse(localStorage.getItem('FavouritesMovies'));

    if(!localStorage.FavouritesMovies){
      return 'heart-outline';
    }

    if (customerAndFavouriteFilms) {
      if (customer) {
        let filmsArray = new Array<Number>();
        filmsArray = customerAndFavouriteFilms[customer.customerId];
        if (filmsArray) {
          if (filmsArray.includes(this.film.filmId)) {
            return 'heart';
          }
        }
        return 'heart-outline';
      } else {
        return 'heart-outline';
      }
    }
  }

  addToFavourite(){
      // let film: FilmDetails;
      const idForFavouriteMoviesForCustomer = [];

      if(!localStorage.FavouritesMovies){
        localStorage.setItem("FavouritesMovies","{}");
      }
      let array = JSON.parse(localStorage.FavouritesMovies);

      if(!this.checkIfCustomerExistsInLocalStorage()){
        array = {
              ...array,
              [this.auth.customerId]: [this.film.filmId],
            };
        console.log(array);
        localStorage.setItem("FavouritesMovies",JSON.stringify(array));
      }
      else{
        let index = array[this.auth.customerId].indexOf(this.film.filmId);
        if(index==-1){
          array[this.auth.customerId].push(this.film.filmId);
        }
        else{
          array[this.auth.customerId].splice(index,1);
        }
        localStorage.setItem("FavouritesMovies",JSON.stringify(array));
      }

      // if (arrayStorageData) {
      //   if (this.checkIfCustomerExistsInLocalStorage()) {
      //     const customer = JSON.parse(localStorage.getItem('AUTH'));
  
      //     arrayStorageData = JSON.parse(
      //       localStorage.getItem('FavouritesMovies')
      //     );  
      //     if (
      //       arrayStorageData[customer.customerId].indexOf(this.film.filmId) >
      //       -1
      //     ) {
      //       arrayStorageData[customer.customerId] =
      //         arrayStorageData[customer.customerId].filter(
      //           (item) => item !== this.film.filmId
      //         );
      //     } else {
      //       idForFavouriteMoviesForCustomer.push(this.film.filmId);
      //       arrayStorageData[customer.customerId] =
      //         idForFavouriteMoviesForCustomer.concat(
      //           arrayStorageData[customer.customerId]
      //         );
      //     }
      //   } else {
      //     idForFavouriteMoviesForCustomer.push(this.film.filmId);
      //     film = this.getFilmById(Number(this.film.filmId), arrayStorageData);
      //     film.favorite = true;
      //     arrayStorageData = {
      //       ...arrayStorageData,
      //       [customer.customerId]: this.idForFavouriteMoviesForCustomer,
      //     };
      //   }
      // } else {
      //   this.idForFavouriteMoviesForCustomer.push(id);
      //   film = this.getFilmById(Number(id));
      //   film.favorite = true;
      //   this.customerAndFavouriteFilms = {
      //     ...this.customerAndFavouriteFilms,
      //     [this.customer.customerId]: this.idForFavouriteMoviesForCustomer,
      //   };
      // }
      // localStorage.setItem(
      //   'FavouritesMovies',
      //   JSON.stringify(this.customerAndFavouriteFilms)
      // );
    
  }

  checkIfCustomerExistsInLocalStorage(): Boolean {
    const customer = JSON.parse(localStorage.getItem('AUTH'));
    const arryStorageData = JSON.parse(localStorage.getItem('FavouritesMovies'));
    let ifCustomerExistsInStorage = false;
    if (
      arryStorageData &&
      arryStorageData[customer.customerId]
    ) {
      ifCustomerExistsInStorage = true;
    }
    return ifCustomerExistsInStorage;
  }

  getFilmById(filmId, filmsForDisplay) {
    let filmById: FilmDetails;
    filmsForDisplay.forEach((film) => {
      if (film.filmId === filmId) {
        filmById = film;
      }
    });
    return filmById;
  }

  isEnoughMoney() {
    if (!this.auth) {
      return false;
    }
    if (this.film.rentalDuration * this.film.rentalRate < this.auth.balance) {
      return true;
    }
    return false;
  }
}
