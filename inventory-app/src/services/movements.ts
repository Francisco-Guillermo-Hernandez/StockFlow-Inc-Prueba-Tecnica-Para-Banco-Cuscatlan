import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '~/environments/environment';
import { Movement } from '~/types/movement';

@Injectable({
  providedIn: 'root',
})
export class Movements {
  private readonly path = environment.ENDPOINT.concat('movements/');

  constructor(private readonly http: HttpClient) {}

  public listAll(): Observable<Array<Movement>> {
    return this.http.get<Array<Movement>>(this.path);
  }

  public productMovements(id: number | string): Observable<Array<Movement>> {
    return this.http.get<Array<Movement>>(`${this.path}${id}`);
  }

  public createProduct(dto: Movement): Observable<Movement> {
    return this.http.post<Movement>(this.path, dto);
  }

}
