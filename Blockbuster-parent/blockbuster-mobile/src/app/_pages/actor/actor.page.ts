import { map } from 'rxjs/operators';
import { FilmService } from 'src/app/_services/film.service';
import { FilmForList } from './../../_models/filmsForList';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-actor',
  templateUrl: './actor.page.html',
  styleUrls: ['./actor.page.scss'],
})
export class ActorPage implements OnInit {

  films: FilmForList[] = [];

  constructor(private filmService: FilmService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.filmService.getFilmsForActor(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe(films => {     
        this.films = films
      });
  }

}
