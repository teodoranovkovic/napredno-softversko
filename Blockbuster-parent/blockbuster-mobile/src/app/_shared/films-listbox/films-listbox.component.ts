/* eslint-disable no-var */
/* eslint-disable @typescript-eslint/no-shadow */

import { THIS_EXPR, ThrowStmt } from '@angular/compiler/src/output/output_ast';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { IonInfiniteScroll } from '@ionic/angular';
import { Customer } from 'src/app/_models/customer.model';
import { FilmForList } from 'src/app/_models/filmsForList';

const LOAD_FILMS_SCROL = 12;

@Component({
  selector: 'app-films-listbox',
  templateUrl: './films-listbox.component.html',

  styleUrls: ['./films-listbox.component.css'],
})
export class FilmsListboxComponent implements OnInit {
  customer: Customer;
  onFavouriteClickHart = 'heart-outline';
  favouriteFilmsForDisplay: FilmForList[];
  displayedMovies = LOAD_FILMS_SCROL;

  arryStorageData: Object;
  favourieMoviesForCustomerMap = new Map<number, number[]>();
  idForFavouriteMoviesForCustomer = new Array<number>();
  arrayStorageMap = new Array<Object>();

  customerAndFavouriteFilms: Object = {};

  limit = 0;
  filmsForDisplay: FilmForList[];

  @ViewChild('infinityScroll') infinityScoll: IonInfiniteScroll;

  @Input('favouriteFilmsClicked')
  set favouriteFilmsClicked(value: Boolean) {
    if (value) {
      this.filterFavouriteFilms();
    } else {
      if (this.filmsForDisplay && this.filmsForDisplay.length > 0) {
        console.log('FILMSFORDISPLAY', this.filmsForDisplay);
        this.displayedMovies = LOAD_FILMS_SCROL;
        this.limit = this.filmsForDisplay.length;
        this.infinityScoll.disabled = false;
      }
    }
    this._favouriteFilmsClicked = value;
  }

  _favouriteFilmsClicked;
  get favouriteFilmsClicked(): Boolean {
    return this._favouriteFilmsClicked;
  }

  @Input('emptyFilteredFilmsForDisplay')
  emptyFilteredFilmsForDisplay = false;

  _films: FilmForList[];

  //ako primi filmove da ih smesti
  @Input('films')
  set films(value: FilmForList[]) {
    if (value && value.length > 0) {
      this.filmsForDisplay = value;
      this.limit = this.filmsForDisplay.length;
      console.log(this.favouriteFilmsClicked);
    }
    if (this.favouriteFilmsClicked) {
      this.filterFavouriteFilms();
    }
    this._films = value;
  }
  get films(): FilmForList[] {
    return this._films;
  }

  _filteredFilms: FilmForList[] = null;
  @Input('filteredFilms') //setere pisemo kad god se desi neka promena;uhvati kad parent salje podatke nove
  set filteredFilms(value: FilmForList[]) {
    if (value && value.length > 0) {
      this.filmsForDisplay = value;
      this.limit = value.length;
      this.displayedMovies = LOAD_FILMS_SCROL;
    } else {
      this.filmsForDisplay = this.films;
      this.limit = this.filmsForDisplay.length;
      this.displayedMovies = LOAD_FILMS_SCROL;
    }
    if (this.favouriteFilmsClicked) {
      this.filterFavouriteFilms();
    }
    this._filteredFilms = value;
  }

  get filteredFilms(): FilmForList[] {
    return this._filteredFilms;
  }

  constructor(public route: Router) {}

  showFavouriteFilsinList() {}
  ngOnInit() {
    const auth = localStorage.AUTH;
    if (auth) {
      this.customer = JSON.parse(auth);
    }

    this.arryStorageData = JSON.parse(localStorage.getItem('FavouritesMovies'));
    if (this.filmsForDisplay) {
      this.showFavouriteFilsinList();
    }
  }

  filterFavouriteFilms() {
    this.favouriteFilmsForDisplay = this.filmsForDisplay;
    this.favouriteFilmsForDisplay = this.favouriteFilms(
      this.favouriteFilmsForDisplay
    );
    this.limit = this.favouriteFilmsForDisplay.length;
    this.infinityScoll.disabled = false;
  }
  isFilmFavourite(filmID) {

    if(!localStorage.FavouritesMovies){
      return 'heart-outline';
    }

    let film: FilmForList;
    this.customer = JSON.parse(localStorage.getItem('AUTH'));
    this.arryStorageData = JSON.parse(localStorage.getItem('FavouritesMovies'));
    this.customerAndFavouriteFilms = this.arryStorageData;

    if (this.arryStorageData) {
      if (this.customer) {
        let filmsArray = new Array<Number>();
        filmsArray = this.customerAndFavouriteFilms[this.customer.customerId];
        if (filmsArray) {
          if (filmsArray.includes(filmID)) {
            return (this.onFavouriteClickHart = 'heart');
          }
        }
        return (this.onFavouriteClickHart = 'heart-outline');
      } else {
        return (this.onFavouriteClickHart = 'heart-outline');
      }
    }
  }

  loadNextData(event) {
    setTimeout(() => {
      this.displayedMovies += LOAD_FILMS_SCROL;
      if (this.displayedMovies > this.limit) {
        event.target.complete();
        event.target.disabled = true;
      } else {
        event.target.complete();
      }
      console.log('displayedMovies', this.displayedMovies);
    }, 2000);
  }

  onFavouritesClick(id) {
    let film: FilmForList;
    this.idForFavouriteMoviesForCustomer = [];
    console.log('add movie fav', this.idForFavouriteMoviesForCustomer);

    if (this.arryStorageData) {
      if (this.checkIfCustomerExistsInLocalStorage()) {
        this.customer = JSON.parse(localStorage.getItem('AUTH'));

        this.arryStorageData = JSON.parse(
          localStorage.getItem('FavouritesMovies')
        );
        this.customerAndFavouriteFilms = this.arryStorageData;
        console.log(
          'niz filmova',
          this.customerAndFavouriteFilms[this.customer.customerId]
        );

        if (
          this.customerAndFavouriteFilms[this.customer.customerId].indexOf(id) >
          -1
        ) {
          this.customerAndFavouriteFilms[this.customer.customerId] =
            this.customerAndFavouriteFilms[this.customer.customerId].filter(
              (item) => item !== id
            );
        } else {
          this.idForFavouriteMoviesForCustomer.push(id);
          console.log(this.customerAndFavouriteFilms[this.customer.customerId]);
          this.customerAndFavouriteFilms[this.customer.customerId] =
            this.idForFavouriteMoviesForCustomer.concat(
              this.arryStorageData[this.customer.customerId]
            );
          console.log('full id', this.idForFavouriteMoviesForCustomer);
        }
      } else {
        this.idForFavouriteMoviesForCustomer.push(id);
        film = this.getFilmById(Number(id));
        film.favorite = true;
        this.customerAndFavouriteFilms = this.arryStorageData;
        this.customerAndFavouriteFilms = {
          ...this.customerAndFavouriteFilms,
          [this.customer.customerId]: this.idForFavouriteMoviesForCustomer,
        };
      }
    } else {
      this.idForFavouriteMoviesForCustomer.push(id);
      film = this.getFilmById(Number(id));
      film.favorite = true;
      this.customerAndFavouriteFilms = {
        ...this.customerAndFavouriteFilms,
        [this.customer.customerId]: this.idForFavouriteMoviesForCustomer,
      };
    }
    localStorage.setItem(
      'FavouritesMovies',
      JSON.stringify(this.customerAndFavouriteFilms)
    );
  }

  checkIfCustomerExistsInLocalStorage(): Boolean {
    this.customer = JSON.parse(localStorage.getItem('AUTH'));
    this.arryStorageData = JSON.parse(localStorage.getItem('FavouritesMovies'));
    let ifCustomerExistsInStorage = false;
    if (
      this.arryStorageData &&
      this.arryStorageData[this.customer.customerId]
    ) {
      ifCustomerExistsInStorage = true;
    }
    return ifCustomerExistsInStorage;
  }

  public favouriteFilms(allFilmsForShow: FilmForList[]) {
    const films = new Array<FilmForList>();
    const customer = JSON.parse(localStorage.getItem('AUTH'));
    const arrayStorage = JSON.parse(localStorage.getItem('FavouritesMovies'));
    this.customerAndFavouriteFilms = arrayStorage;
    if (this.arryStorageData) {
      if (this.checkIfCustomerExistsInLocalStorage()) {
        let filmsArray = new Array<Number>();
        filmsArray = this.customerAndFavouriteFilms[customer.customerId];
        console.log(this.customerAndFavouriteFilms[customer.customerId]);
        filmsArray.forEach((filmID) => {
          const film = this.getFavouriteFilmById(filmID, allFilmsForShow);
          if (film) {
            films.push(film);
          }
        });
      }
    }
    console.log('FILMS', films);
    return films;
  }

  getFilmById(filmId) {
    let filmById: FilmForList;
    this.filmsForDisplay.forEach((film) => {
      if (film.filmId === filmId) {
        filmById = film;
      }
    });
    return filmById;
  }

  getFavouriteFilmById(filmId, findFilms: FilmForList[]) {
    let filmById: FilmForList;
    findFilms.forEach((film) => {
      if (film.filmId === filmId) {
        filmById = film;
      }
    });
    return filmById;
  }
}
