import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';
import { FilmForList } from 'src/app/_models/filmsForList';

import { FilmService } from 'src/app/_services/film.service';

@Component({
  selector: 'app-films-categories',
  templateUrl: './films-categories.page.html',
  styleUrls: ['./films-categories.page.scss'],
})
export class FilmsCategoriesPage implements OnInit {


  filteredFilms: FilmForList[];

  constructor(public filmService: FilmService,
    public route: ActivatedRoute) {
    this.filteredFilms = new Array<FilmForList>();
  }


  ngOnInit() {
    this.loadFilteredFilms();

  }
  loadFilteredFilms() {
    this.filmService
      .getFilmsByCategory(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe(allFilms => {
        this.filteredFilms = allFilms;
        console.log(this.filteredFilms);
      });

  }

  getCategoryName() {
    if (this.filteredFilms && this.filteredFilms.length) {
      return this.filteredFilms[0].categoryName;
    }

  }

}
