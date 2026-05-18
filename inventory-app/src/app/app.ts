import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { Loader } from "~/services/loader/loader";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, Loader],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('inventory-app');
}
