import { DarkModeService } from './../../../_services/dark-mode.service';
import { Component, OnInit } from '@angular/core';
import { MenuController } from '@ionic/angular';
import { Link } from 'src/app/_models/navigation/link.model';
import { Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss'],
})
export class SideMenuComponent implements OnInit {
  constructor(private menu: MenuController, private darkModeService: DarkModeService) { }

  items: Link[] = [
    { text: 'Actors', router: ['/actors'], icon: 'person-outline' },
    { text: 'Search', router: ['/films'], icon: 'search-outline' },
    { text: 'Contact', router: ['/contact-us'], icon: 'mail-outline' },
    {
      text: 'About us',
      router: ['/about-us'],
      icon: 'information-circle-outline',
    },
  ];

  dark = false;

  @Output() darkForParent = new EventEmitter<boolean>();

  ngOnInit() {
    if (localStorage.DARK) {
      this.dark = true;
    }
  }

  openFirst() {
    this.menu.enable(true, 'first');
    this.menu.open('first');
  }

  openEnd() {
    this.menu.open('end');
  }

  openCustom() {
    this.menu.enable(true, 'custom');
    this.menu.open('custom');
  }

  sendDarkMode() {
    this.darkForParent.emit(this.dark);
    this.darkModeService.publishAuth(this.dark);
  }
}
