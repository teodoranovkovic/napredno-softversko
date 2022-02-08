import { Component, Input, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { FilmForList } from 'src/app/_models/filmsForList';

const ASC = "asc";
const DESC = "desc";

@Component({
  selector: 'app-sort-films',
  templateUrl: './sort-films.component.html',
  styleUrls: ['./sort-films.component.css']
})
export class SortFilmsComponent implements OnInit {

  _films: FilmForList[];

  @Output()
  sortFilmsFinishedSubject = new Subject();

  @Output()
  sortFilterdFilmsFinishedSubject = new Subject();

  @Input('films')
  set films(value: FilmForList[]) {
    this._films = value;
    if (value && value.length > 0) {
      this.onSortTypeChange('1');
    }
  }

  get films(): FilmForList[] {
    return this._films;
  }

  @Input('filteredFilms')
  filteredFilms: FilmForList[];


  sortTypes = [
    { id: 0, name: "Rating ascending" },
    { id: 1, name: "Rating descending" },
    { id: 2, name: "Year ascending" },
    { id: 3, name: "Year descending" },
    { id: 4, name: "Title ascending" },
    { id: 5, name: "Title descending" },
  ];

  selectedSort = new String();

  constructor() { }

  ngOnInit() {
  }

  onSortTypeChange(event) {
    if (event.target != undefined) {
      this.selectedSort = event.target.value;
      if (this.selectedSort === '0') {

        if (this.filteredFilms && this.filteredFilms.length > 0) {
          this.sortFilmsByRating(this.filteredFilms, ASC);
        }
        if (this.films && this.films.length > 0) {
          this.sortFilmsByRating(this.films, ASC);
        }
      }
      else if (this.selectedSort === '1') {

        if (this.filteredFilms && this.filteredFilms.length > 0) {
          this.sortFilmsByRating(this.filteredFilms, DESC);
        }
        if (this.films && this.films.length > 0) {
          this.sortFilmsByRating(this.films, DESC);
        }
      }
      else if (this.selectedSort === '2') {

        if (this.filteredFilms && this.filteredFilms.length > 0) {
          this.sortFilmsByReleaseYear(this.filteredFilms, ASC);
        }
        if (this.films && this.films.length > 0) {
          this.sortFilmsByReleaseYear(this.films, ASC);
        }

      }
      else if (this.selectedSort === '3') {
        if (this.filteredFilms && this.filteredFilms.length > 0) {
          this.sortFilmsByReleaseYear(this.filteredFilms, DESC);

        }
        if (this.films && this.films.length > 0) {
          this.sortFilmsByReleaseYear(this.films, DESC);
        }
      }
      else if (this.selectedSort === '4') {
        if (this.filteredFilms && this.filteredFilms.length > 0) {
          this.sortFilmsByTitle(this.filteredFilms, ASC);

        }
        if (this.films && this.films.length > 0) {
          this.sortFilmsByTitle(this.films, ASC);
        }
      }
      else if (this.selectedSort === '5') {

        if (this.filteredFilms && this.filteredFilms.length > 0) {
          this.sortFilmsByTitle(this.filteredFilms, DESC);

        }
        if (this.films && this.films.length > 0) {
          this.sortFilmsByTitle(this.films, DESC);
        }
      }
      // this.sortFilmsFinishedSubject.next(this.films);
      // this.sortFilterdFilmsFinishedSubject.next(this.films);
    }
  }

  customPopoverReleaseYear: any = {
    header: 'Sort films',
  };

  sortFilmsByTitle(allFilms: FilmForList[], orderTaype) {
    if (orderTaype === "asc") {
      allFilms.sort((m1, m2) => {
        return m1.title > m2.title ? 1 : (m2.title > m1.title ? -1 : 0); // ASCENDING
      });
    }
    else {
      allFilms.sort((m1, m2) => {
        return m1.title > m2.title ? -1 : (m2.title > m1.title ? 1 : 0); // DESC
      });
    }
  }

  sortFilmsByReleaseYear(allFilms: FilmForList[], orderTaype) {
    if (orderTaype === "asc") {
      allFilms.sort((m1, m2) => {
        return m1.releaseYear > m2.releaseYear ? 1 : (m2.releaseYear > m1.releaseYear ? -1 : 0); // ASCENDING
      });
    }
    else {
      allFilms.sort((m1, m2) => {
        return m1.releaseYear > m2.releaseYear ? -1 : (m2.releaseYear > m1.releaseYear ? 1 : 0); // DESC
      });
    }
  }

  sortFilmsByRating(allFilms: FilmForList[], orderTaype) {
    if (orderTaype === "asc") {
      allFilms.sort((m1, m2) => {
        return m1.starRating > m2.starRating ? 1 : (m2.starRating > m1.starRating ? -1 : 0); // ASCENDING
      });
    }
    else {
      allFilms.sort((m1, m2) => {
        return m1.starRating > m2.starRating ? -1 : (m2.starRating > m1.starRating ? 1 : 0); // DESC
      });
    }
  }


  // sortFilmsBy(allFilms: FilmForList[], sortType, orderType) {
  //   if (sortType === "rating" && orderType === ASC) {
  //     allFilms.sort((m1, m2) => {
  //       return m1.starRating > m2.starRating ? 1 : (m2.starRating > m1.starRating ? -1 : 0); // ASCENDING
  //     });
  //   }
  //   else if (sortType === "rating" && orderType === DESC) {
  //     allFilms.sort((m1, m2) => {
  //       return m1.starRating > m2.starRating ? -1 : (m2.starRating > m1.starRating ? 1 : 0); // DESC
  //     });
  //   }
  // }


}
