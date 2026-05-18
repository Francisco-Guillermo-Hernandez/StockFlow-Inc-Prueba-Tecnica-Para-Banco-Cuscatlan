import { Routes } from '@angular/router';
import { Details } from './details/details';
import { Register } from './register/register';

export const routes: Routes = [
  {
    path: '',
    component: Details,
  },
  {
    path: 'register',
    component: Register,
  },
]
