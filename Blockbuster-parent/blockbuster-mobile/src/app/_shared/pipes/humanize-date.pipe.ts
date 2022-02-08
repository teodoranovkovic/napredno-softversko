import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'humanizeDate',
})
export class HumanizeDatePipe implements PipeTransform {
  transform(value: Date): string {
    return moment(value).fromNow();
  }
}
