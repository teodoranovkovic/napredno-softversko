import { FilmForSlider } from './../../_models/filmsForSlider';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-film-card',
  templateUrl: './film-card.component.html',
  styleUrls: ['./film-card.component.scss'],
})
export class FilmCardComponent implements OnInit {

  @Input()
  film: FilmForSlider;

  constructor() { }

  ngOnInit() {}

}
