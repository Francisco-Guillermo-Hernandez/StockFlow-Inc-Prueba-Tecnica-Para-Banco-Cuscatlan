import { Routes } from '@angular/router';
import { Details } from './details/details';
import { Register } from './register/register';
import { History } from './history/history';

export const routes: Routes = [
  {
    path: '',
    component: Details,
    data: { mode: 'all' }
  },
  {
    path: 'by-product/:id',
    component: Details,
    data: { mode: 'by-product' }
  },
  {
    path: 'register/:id',
    component: Register,
    data: { mode: 'edit' }
  },
   {
    path: 'register',
    component: Register,
    data: { mode: 'create' }
  },
  {
    path: 'history/:id',
    component: History,
  },
]
