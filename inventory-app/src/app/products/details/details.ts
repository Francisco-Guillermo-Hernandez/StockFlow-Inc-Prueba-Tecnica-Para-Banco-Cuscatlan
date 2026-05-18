import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { environment } from '../../../environments/environment';

type Product = {
  id?: number,
  sku: string,
  name: string,
  description: string,
  currentStock: number,
  minStock: number,
  unitPrice: number
  weight: number,
  active: boolean,
  createdAt?: string,
  updatedAt?: string
}

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './details.html',
  styleUrl: './details.css',
})
export class Details implements OnInit {
  products: Product[] = [];
  loading = true;
  error: any = null;

  ngOnInit(): void {
    fetch(environment.ENDPOINT + 'products/')
      .then((res) => {
        if (!res.ok) throw new Error('Network response was not ok: ' + res.status);
        return res.json();
      })
      .then((data) => {
        console.log(data);
        this.products = Array.isArray(data) ? data : data?.items ?? [];
      })
      .catch((err) => (this.error = err))
      .finally(() => (this.loading = false));
  }
}
