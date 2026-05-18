import { Component, OnInit, signal, Signal } from '@angular/core';
import { Movements } from '~/services/movements';
import { Movement } from '~/types/movement';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [],
  templateUrl: './details.html',
  styleUrl: './details.css',
})
export class Details implements OnInit {

  public loading = signal<boolean>(false);
  public movements = signal<Array<Movement>>([]);
  public error = signal(null);

  constructor(private readonly service: Movements) {}

  public ngOnInit(): void {
    this.loading.set(true);
    this.service.listAll().subscribe({
      next: (movements) => {
        this.movements.set(movements);
        this.loading.set(false);
      },
      error: (error) => {
        this.loading.set(false);
        this.error.set(error);
      }
    })
  }

}
