import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FilmForList } from 'src/app/_models/filmsForList';

@Component({
  selector: 'app-film-poster',
  templateUrl: './film-poster.component.html',
  styleUrls: ['./film-poster.component.scss'],
})
export class FilmPosterComponent implements OnInit {

  @Input("film")
  film: FilmForList;

  constructor(public route: Router) { }

  ngOnInit() { }

  onFilmClick() {
    this.route.navigate(['film', this.film.filmId]);
  }

}
