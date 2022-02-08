import { Component, Input, OnInit } from '@angular/core';
import { Filter } from 'src/app/_models/filter.model';
import { FilterService } from 'src/app/_services/filter.service';

@Component({
  selector: 'app-tab-filter',
  templateUrl: './tab-filter.component.html',
  styleUrls: ['./tab-filter.component.scss'],
})
export class TabFilterComponent implements OnInit {
  @Input() filter: Filter;

  constructor(private filterService: FilterService) {}

  ngOnInit() {}

  segmentChanged(event: Event) {
    const filter = (event.target as HTMLInputElement).value;
    this.filterService.publishFilter(this.filter.type, filter);
  }
}
