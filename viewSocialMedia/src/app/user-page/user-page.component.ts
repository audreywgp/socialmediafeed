import { getLocaleDateFormat } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserData } from '../user-data';
import { UserDetails } from '../user-details';
import { UserdataService } from '../userdata.service';
import { UserdetailsService } from '../userdetails.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css'],
})
export class UserPageComponent implements OnInit {
  userData: UserData[] | undefined;
  userId: number | undefined;
  username: any | undefined;
  usernameList: any[] = [];
  displayStyle = 'none';
  postType: string | undefined;

  constructor(
    private userDetailsService: UserdetailsService,
    private userDataService: UserdataService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  // do something about the date
  ngOnInit(): void {
    this.userDataService.getAllUserdata().subscribe((data: UserData[]) => {
      for (let x of data) {
        this.userDetailsService
          .getUserById(x.userId!)
          .subscribe((data: UserDetails) => {
            this.usernameList.push(data.username);
            console.log(
              'this is list of usernamefor the post' + this.usernameList
            );
          });
      }
      this.userData = data;
      this.username = sessionStorage.getItem('username');
    });
  }

  openPopup() {
    this.displayStyle = 'block';
  }
  closePopup() {
    this.displayStyle = 'none';
  }

  onVideoPost() {
    this.postType = 'video';
    sessionStorage.setItem('postType', this.postType!);
    this.router.navigate(['share', sessionStorage.getItem('username')]);
  }
  onImagePost() {
    this.postType = 'image';
    sessionStorage.setItem('postType', this.postType!);
  }

  // ---- class end
}

// ngOnInit(): void {
//   console.log('running in ngOnInIt');
//   this.userDetailsService.passedUser().subscribe((id) => {
//     this.userId = id;
//     console.log('from passed user' + id);
//     console.log('running in subscribe');
//     this.userDataService.getAllUserdata().subscribe((data: UserData[]) => {
//       // for (let x of data) {this.userDetailsService.getUserById();
//       //   console.log('data:' + x.createdDate);
//       // }
//       this.userData = data;
//     });
//     this.userDetailsService.passedUsername().subscribe((username) => {
//       this.username = username; // user's user name
//     });

//   });
// }

// for (let x of data) {
//   console.log('data:' + x.createdDate);
//   this.userDetailsService.getUserById(x.userId!).subscribe((details) => {
//     console.log;
//     console.log(details);
//   });
// }
