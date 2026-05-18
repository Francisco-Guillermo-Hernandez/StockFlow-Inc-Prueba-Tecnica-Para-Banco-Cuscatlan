import { Injectable } from '@angular/core';
import { environment } from '~/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Alert } from '~/types/alert';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Alerts {

  private readonly productsPath = environment.ENDPOINT.concat('alerts/');
  constructor(private readonly http: HttpClient) {}

  public listAll(): Observable<Alert[]> {
      return this.http.get<Alert[]>(this.productsPath);
    }
}
