import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'money',
})
export class MoneyPipe implements PipeTransform {
  transform(value: number): string {
    const str = Number(value).toFixed(2) + '$';
    return str;
  }
}
