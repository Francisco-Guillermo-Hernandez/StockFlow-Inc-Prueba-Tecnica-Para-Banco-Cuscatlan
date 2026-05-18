import { Component, OnInit, signal } from '@angular/core';
import { Alert } from '~/types/alert';
import { Alerts } from '~/services/alerts';
import { Loader } from "~/services/loader/loader";

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [Loader],
  templateUrl: './details.html',
  styleUrl: './details.css',
})
export class Details implements OnInit {
  public alerts = signal<Array<Alert>>([]);
  public isLoading = signal<boolean>(false);
  public error = null;
  constructor(private readonly service: Alerts) {}

  public ngOnInit(): void {
    this.listAll();
  }

  public listAll(): void {
    this.isLoading.set(true);
    this.service.listAll().subscribe({
      next: (data) => {
        this.alerts.set(data);
        this.isLoading.set(false);
      },
      error: (error) => {
        console.error(error);
        this.isLoading.set(false);
        this.error = error;
      },
    });
  }
}
