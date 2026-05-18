import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { routes } from './routes';
import { Details } from './details/details';

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes), Details],
})
export class AlertsModule {}
