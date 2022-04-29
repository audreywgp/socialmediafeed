import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDetails } from '../user-details';
import { UserdetailsService } from '../userdetails.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})
export class SignUpComponent implements OnInit {
  user: UserDetails = new UserDetails();
  // inputusername!: string | undefined;
  // inputpassword!: string;
  inputCfmPassword: string | undefined;
  // isShown:boolean=false
  checkNum = 0;
  msg = '';

  userObj = {};

  constructor(
    private userdetailsService: UserdetailsService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {}

  onSignUp() {
    this.userdetailsService.getUser(this.user.username).subscribe(
      (data) => {
        this.msg = 'username exist';
        this.inputCfmPassword = '';
        this.user.password = '';
      },
      (error) => {
        if (this.user.password != this.inputCfmPassword) {
          this.router.navigate(['signup']);
          this.msg = 'password not matching';
        } else {
          this.userObj = {
            name: this.user.name,
            username: this.user.username,
            password: this.user.password,
            role: 'user',
          };
          this.userdetailsService.addUser(this.userObj).subscribe(
            (data) => {
              console.log(this.userObj);
              console.log('signed up');
              alert('routing to login page, please login');
              this.router.navigate(['']); // route to login
            },
            (error) => {
              console.log('empty fields, cant add to db');
              this.router.navigate(['signup']);
              this.msg = 'more than one field is not filled';
            }
          );
        }
      }
    );
  }
}
