import { Routes } from '@angular/router';
import { Details } from './details/details';
import { Register } from './register/register';
import { History } from './history/history';

export const routes: Routes = [
  {
    path: '',
    component: Details,
  },
  {
    path: 'register/:id',
    component: Register,
  },
  {
    path: 'history/:id',
    component: History,
  },
]
