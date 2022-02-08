import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { map } from 'rxjs/operators';
import { FilmForList } from '../_models/filmsForList';
import { FilmService } from '../_services/film.service';

@Component({
  selector: 'app-films',
  templateUrl: './films.component.html',
  styleUrls: ['./films.component.css'],
})
export class FilmsComponent implements OnInit {
  films: FilmForList[];
  filteredFilms: FilmForList[];

  emtyFilteredFilms = false;

  searchFilterEnabled = false;
  categoryFilterEnabled = false;

  favouriteFilmsClicked = false;



  @ViewChild('searchFilter') searchFilterComponent;
  @ViewChild('categoryFilter') categoryFilterComponent;
  @ViewChild('sortFilms') sortFilms;
  @ViewChild('filmSlider') filmSliderComponent;

  constructor(
    private filmsService: FilmService,
    private changeDetectorRef: ChangeDetectorRef
  ) {
    this.films = new Array<FilmForList>();
    this.filteredFilms = new Array<FilmForList>();
  }

  ngOnInit() {
    this.loadFilms();
  }

  onSearchFinished($event) {
    this.emtyFilteredFilms = false;
    if ($event === 'discard') {
      this.filteredFilms = null; //tell to listbox componenet to display all films
      if (this.categoryFilterEnabled) {
        this.categoryFilterComponent.filterFilmsByCategory(this.films);
      }
      this.searchFilterEnabled = false;
    } else if ($event && $event.length > 0) {
      this.filteredFilms = $event;
      console.log($event);
      this.searchFilterEnabled = true;
      if (this.categoryFilterEnabled) {
        this.categoryFilterComponent.filterFilmsByCategory(this.filteredFilms);
        // this.sortFilms.
      }
    } else {
      this.emtyFilteredFilms = true;
    }
  }

  onCategoryFilterFinished($event) {
    this.emtyFilteredFilms = false;
    if ($event === 'discard') {
      this.filteredFilms = null; //tell to listbox componenet to display all films
      if (this.categoryFilterComponent.getSelectedCategory() === 'All') {
        this.categoryFilterEnabled = false;
      }
      if (this.searchFilterEnabled) {
        this.searchFilterComponent.searchFilms(this.films);
      }
    } else if ($event && $event.length > 0) {
      this.filteredFilms = $event;
      console.log($event);
      this.categoryFilterEnabled = true;
    } else {
      this.emtyFilteredFilms = true;
    }
  }

  onChategoryChanged($event) {
    this.filmSliderComponent.categoryChanged($event);
  }

  // onSortFilmsFinished($event) {
  //   this.films = $event;
  //   this.changeDetectorRef.detectChanges();
  // }

  // onSortFilteredFinished($event) {
  //   this.filteredFilms = $event;
  //   this.changeDetectorRef.detectChanges();
  // }

  loadFilms() {
    this.filmsService.getFilmsForList().subscribe((allFilms) => {
      this.films = allFilms;
    });
  }
  onFavouriteFilmsClick($event) {
    this.favouriteFilmsClicked = $event;

  }
}
