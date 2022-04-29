import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  log: Boolean = true;

  constructor(private router: Router) {}

  onLogout() {
    this.log != this.log;
    this.router.navigate(['']); // route to login
    sessionStorage.clear();
  }
}
