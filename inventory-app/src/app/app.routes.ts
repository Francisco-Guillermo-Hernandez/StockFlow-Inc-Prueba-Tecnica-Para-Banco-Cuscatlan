import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'products',
    pathMatch: 'full',
  },
  {
    path: 'products',
    loadChildren: () => import('./products/products-module').then(m => m.ProductsModule),
  },
  {
    path: 'movements',
    loadChildren: () => import('./movements/movements-module').then(m => m.MovementsModule),
  },
  {
    path: 'alerts',
    loadChildren: () => import('./alerts/alerts-module').then(m => m.AlertsModule),
  },
];
