import { Component, OnInit, signal, Signal } from '@angular/core';
import { Movements } from '~/services/movements';
import { Movement } from '~/types/movement';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [],
  templateUrl: './details.html',
  styleUrl: './details.css',
})
export class Details implements OnInit {

  public productId = signal<string | null>('');
  public loading = signal<boolean>(false);
  public movements = signal<Array<Movement>>([]);
  public error = signal(null);
  public mode = signal<string>('all');

  constructor(
    private readonly service: Movements,
    private readonly router: Router,
    private readonly route: ActivatedRoute,
  ) {}

  public ngOnInit(): void {

    const id = this.route.snapshot.paramMap.get('id');
    const mode = this.route.snapshot.data['mode'];
    this.productId.set(id);
    this.mode.set(mode);

    if (this.mode() === 'all') {
      this.listAll();
    } else {
      this.listByProduct();
    }
  }

  public listAll(): void {
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
    });
  }

  public listByProduct(): void {
    this.loading.set(true);
    this.service.productMovements(this.productId()?? '').subscribe({
      next: movements => {
        this.loading.set(false);
        this.movements.set(movements);
      },
      error: error => {
        this.loading.set(false);
        console.error(error);
        this.error.set(error);
      }
    })
  }

}
