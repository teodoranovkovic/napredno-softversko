import { Component, Input, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { Auth } from 'src/app/_models/auth.model';
import { FilmForList } from 'src/app/_models/filmsForList';
import { FilmService } from 'src/app/_services/film.service';

@Component({
  selector: 'app-search-films',
  templateUrl: './search-films.component.html',
  styleUrls: ['./search-films.component.css'],
})
export class SearchFilmsComponent implements OnInit {
  heartName = 'heart-outline';

  searchText = new String();

  @Input('films')
  films: FilmForList[];

  @Input('filteredFilms')
  filteredFilms: FilmForList[];

  @Output()
  searchFinishedSubject = new Subject();

  @Output()
  favouriteFilmsFinishedSubject = new Subject();

  @Input('searchFilterEnabled')
  searchFilterEnabled = false;

  @Input('categoryFilterEnabled')
  categoryFilterEnabled = false;

  listBefourFavouriteFilms: FilmForList[];

  auth: Auth;

  constructor(private servisFilm: FilmService) {}

  ngOnInit() {
    const auth = localStorage.AUTH;
    if (auth) {
      this.auth = JSON.parse(auth);
    }
  }

  filterFilmSearch(event) {
    this.searchText = event.target.value;
    if (this.filteredFilms && this.filteredFilms.length > 0) {
      this.searchFilms(this.filteredFilms);
    } else {
      this.searchFilms(this.films);
    }
  }

  public searchFilms(allFilms: FilmForList[]) {
    const filterFilms = new Array<FilmForList>();
    if (this.searchText.trim().length !== 0) {
      allFilms.forEach((film: FilmForList, id: number) => {
        if (
          film.title
            .toLowerCase()
            .includes(this.searchText.toString().toLowerCase())
        ) {
          filterFilms.push(film);
        }
      });
      this.searchFinishedSubject.next(filterFilms);
    } else {
      this.searchFinishedSubject.next('discard');
    }
  }

  showFavourites() {
    if (this.heartName === 'heart-outline') {
      this.heartName = 'heart';
      this.favouriteFilmsFinishedSubject.next(true);
      // if (this.filteredFilms && this.filteredFilms.length > 0) {
      //   this.favouriteFilms(this.filteredFilms);
      // }
      // else {
      //   this.favouriteFilms(this.films);
      // }
    } else {
      this.heartName = 'heart-outline';
      this.favouriteFilmsFinishedSubject.next(false);
      // if (this.filteredFilms && this.filteredFilms.length > 0) {
      //   this.searchFinishedSubject.next(this.listBefourFavouriteFilms);
      //   //this.searchFinishedSubject.next(this.films);
      // }
      // else {
      //   this.searchFinishedSubject.next(this.films);
      // }
    }
  }
}
