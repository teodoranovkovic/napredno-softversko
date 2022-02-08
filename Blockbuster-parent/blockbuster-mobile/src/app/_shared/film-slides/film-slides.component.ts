import { AppConfig } from 'src/app/app-config';
import { FilmService } from './../../_services/film.service';
import { FilmForSlider } from './../../_models/filmsForSlider';
import { Component, Input, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-film-slides',
  templateUrl: './film-slides.component.html',
  styleUrls: ['./film-slides.component.scss'],
})
export class FilmSlidesComponent implements OnInit {
  @Input()
  type: 'for you' | 'most wanted' | 'new movies' | 'top rated movies' | 'category';

  category: String;

  title: String;

  films: FilmForSlider[];

  slideOpts = {
    slidesPerView: 1.5,
    speed: 1000,

    autoplay: {
      delay: 3000,
      disableOnInteraction: false,
    },
    loop: true,
  };

  constructor(private servisFilm: FilmService) { }


  categoryChanged(category) {

    this.category = category;
    console.log(this.category);



    if (category == "All") {

      this.title = "Top rated movies";

      this.withoutCategoryFillter();
    }
    else {
      this.title = "Top rated " + category + " movies";

      this.servisFilm
        .getFilmByCategoryForSlider(category)
        .pipe(
          map((films) => {
            return films.map((film) => ({
              ...film,
              posterUrl: `https://picsum.photos/seed/${Math.random() * 50
                }/480/270`,
            }));
          })
        )
        .subscribe(films => {
          this.films = films;
        });
    }
  }

  withoutCategoryFillter() {

    this.servisFilm
      .getFilmsForType(this.type)
      .pipe(
        map((films) => {
          return films.map((film) => {
            if (film.posterUrl) return film;
            return {
              ...film,
              posterUrl: `https://picsum.photos/seed/${Math.random() * 50
                }/480/270`,
            };
          });
        })
      )
      .subscribe((films) => {
        // console.log("sdgldfs;g");
        // alert(AppConfig.API_ENDPOINT);
        this.films = films;
      });
  }


  ngOnInit() {

    if (this.type == "category")
      this.title = "Top rated movies";
    else
      this.title = this.type;

    this.withoutCategoryFillter();
  }
}
