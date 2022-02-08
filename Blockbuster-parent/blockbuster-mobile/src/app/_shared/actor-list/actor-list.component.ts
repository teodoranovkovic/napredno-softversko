import { Component, Input, OnInit } from '@angular/core';
import { Actor } from 'src/app/_models/actor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-actor-list',
  templateUrl: './actor-list.component.html',
  styleUrls: ['./actor-list.component.scss'],
})
export class ActorListComponent implements OnInit {
  @Input() actors: Actor[];

  constructor(public route: Router) {}

  ngOnInit() {}

  onActorClick(id) {
    this.route.navigate(['actor', id]);
  }
  
  onClickActor(actorId) {
    this.route.navigate(['actor', actorId]);
  }
}
