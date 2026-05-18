import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from '~/types/product';
import { Products } from '~/services/products';
import { RouterLink } from '@angular/router';
import { signal } from '@angular/core';
import { Loader } from "~/services/loader/loader";


@Component({
  selector: 'app-details',
  standalone: true,
  imports: [CommonModule, RouterLink, Loader],
  templateUrl: './details.html',
  styleUrl: './details.css',
})
export class Details implements OnInit {
  products = signal<Array<Product>>([]);

  loading = signal<boolean>(false);
  error: any = null;

  constructor(private readonly productsService: Products) {}

  public ngOnInit(): void {

    this.loading.set(true);
    this.productsService.listAll().subscribe({
      next: (data) => {
        this.products.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        this.error = err;
        this.loading.set(false);
      },
    });
  }
}
