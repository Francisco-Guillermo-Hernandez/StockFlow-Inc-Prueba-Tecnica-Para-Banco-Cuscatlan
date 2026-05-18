import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '~/environments/environment';
import { Product } from '~/types/product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Products {
  private readonly productsPath = environment.ENDPOINT.concat('products/');

  constructor(private readonly http: HttpClient) {}

  public listAll(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productsPath);
  }

  public productById(id: number | string): Observable<Product> {
    return this.http.get<Product>(`${this.productsPath}${id}`);
  }

  public createProduct(productDto: Product): Observable<Product> {
    return this.http.post<Product>(this.productsPath, productDto);
  }

  public updateProduct(id: number | string, productDto: Product): Observable<Product> {
    return this.http.patch<Product>(`${this.productsPath}${id}`, productDto);
  }
}
