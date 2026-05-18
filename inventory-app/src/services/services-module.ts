import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Alerts } from '~/services/alerts';
import { Movements } from '~/services/movements';
import { Products } from '~/services/products';

@NgModule({
  imports: [CommonModule],
  providers: [Alerts, Movements, Products],
  exports: [CommonModule],
})
export class ServicesModule {}
