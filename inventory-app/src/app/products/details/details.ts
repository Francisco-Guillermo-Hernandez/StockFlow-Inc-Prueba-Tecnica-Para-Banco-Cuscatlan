import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from '~/types/product';
import { Products } from '~/services/products';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './details.html',
  styleUrl: './details.css',
})
export class Details implements OnInit {
  products: Product[] = [];
  loading = true;
  error: any = null;

  constructor(private readonly productsService: Products) {}

  ngOnInit(): void {
    this.productsService.listAll().subscribe({
      next: (data) => {
        this.products = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = err;
        this.loading = false;
      },
    });
  }
}
