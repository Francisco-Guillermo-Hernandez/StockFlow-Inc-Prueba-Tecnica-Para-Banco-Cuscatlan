import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { routes } from './route';
import { Details } from './details/details';
import { Register } from './register/register';

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes), Details, Register],
})
export class MovementsModule {}
