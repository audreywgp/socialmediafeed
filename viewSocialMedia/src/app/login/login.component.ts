import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDetails } from '../user-details';
import { UserdetailsService } from '../userdetails.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  user: UserDetails = new UserDetails();
  msg = '';
  isShown: Boolean = true;
  mysession: any;

  constructor(
    private userdetailsService: UserdetailsService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // this.inputusername = this.route.snapshot.params['inputusername']
    // this.inputpassword = this.route.snapshot.params['inputpassword']
    if (sessionStorage.getItem('username')?.length) {
      sessionStorage.clear();
    }
  }

  // if cannot login, username reset/ password reset
  onLogin() {
    sessionStorage.clear();
    this.userdetailsService.getUser(this.user.username).subscribe(
      (data) => {
        if (data) {
          console.log('login');
          console.log(sessionStorage.getItem('username'));
          this.user = data;
          this.mysession = sessionStorage.setItem('role', data.role!);
          this.mysession = sessionStorage.setItem('username', data.username!);
          this.mysession = sessionStorage.setItem('name', data.name!);
          this.mysession = sessionStorage.setItem(
            'userId',
            data.userId?.toString()!
          );
          // this.userdetailsService.sendUserId(data.userId!);
          // this.userdetailsService.sendUsername(data.username!);
          if (
            data.role?.toLowerCase() === 'user' ||
            data.role?.toLowerCase() === 'admin'
          ) {
            this.goUserpage();
          } else {
            console.log('you are admin');
          }
        } else {
          this.msg = 'wrong password';
        }
      },
      (error) => {
        this.msg = 'wrong username';
      }
    );
  }

  goUserpage() {
    this.router.navigate(['user', this.user?.username]);
  }
  onSignUp() {
    this.router.navigate(['signup']);
  }
}
