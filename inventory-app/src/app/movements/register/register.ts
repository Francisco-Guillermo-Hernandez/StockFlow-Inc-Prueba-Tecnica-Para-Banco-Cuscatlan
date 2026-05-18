import { Component, OnInit, Signal, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Movements } from '~/services/movements';
import { Products } from '~/services/products';
import { Product } from '~/types/product';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register implements OnInit {

  public loading = signal<boolean>(false);
  public product = signal<Product>({
    sku: '',
  name: '',
  description: '',
  currentStock: 1,
  minStock: 1,
  unitPrice: 1,
  weight: 0,
  active: false
  });

  constructor(private readonly route: ActivatedRoute, private readonly productService: Products) {}

  public ngOnInit(): void {
  const id = this.route.snapshot.paramMap.get('id');
    console.log(id);

    if (id !== null) {
      this.productService.productById(id).subscribe({
        next: product => {
          this.loading.set(false);
          this.product.set(product);
        },
        error: (error) => {
          this.loading.set(false);
        }
      })
    }
  }


}
