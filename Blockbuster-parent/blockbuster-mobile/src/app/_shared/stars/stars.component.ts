import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-stars',
  templateUrl: './stars.component.html',
  styleUrls: ['./stars.component.scss'],
})
export class StarsComponent implements OnInit {
  @Input() starRating: number;

  constructor() {}

  ngOnInit() {}

  counter(n: number) {
    return new Array(n);
  }
}
