import { getLocaleDateFormat } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserData } from '../user-data';
import { UserDetails } from '../user-details';
import { UserdataService } from '../userdata.service';
import { UserdetailsService } from '../userdetails.service';
import * as moment from 'moment/moment';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css'],
})
export class UserPageComponent implements OnInit {
  userData: UserData[] = [];
  userId = parseInt(sessionStorage.getItem('userId')!);
  u_name = sessionStorage.getItem('name')!;
  role = sessionStorage.getItem('role');
  timediff: any = [];
  usernameList: any[] = [];
  displayStyleFile = 'none';
  displayStyleEdit = 'none';
  postType: string | undefined;
  config: any;
  changeCaption: string | undefined = '';
  changeCaptionId: number | undefined;
  // showEditOptions = false; // not working

  constructor(
    private userDetailsService: UserdetailsService,
    private userDataService: UserdataService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.config = {
      itemsPerPage: 5,
      currentPage: 1,
      totalItems: this?.userData.length,
    };
  }
  ngOnInit(): void {
    this.userDataService.getAllUserdata().subscribe((data: UserData[]) => {
      for (let x of data) {
        this.timediff.push(moment(x.createdDate).startOf('hour').fromNow());
        // if (this.role === 'admin' || this.userId == x.userId) {
        //   this.showEditOptions = !this.showEditOptions;
        // }

        this.changeCaption = x.caption;
        this.userDetailsService
          .getUserById(x.userId!)
          .subscribe((data: UserDetails) => {
            this.usernameList.push(data.name);
          });
      }
      // date formatter then add in
      this.userData = data;
    });
  }

  openPopup() {
    this.displayStyleFile = 'block';
    console.log(this.role);
  }
  closePopup() {
    this.displayStyleFile = 'none';
    window.location.reload();
  }

  openPopEdit() {
    this.displayStyleEdit = 'block';
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
  pageChanged(event: any) {
    this.config.currentPage = event;
  }

  onEdit(dataid: any, dataUserid: any) {
    // this.router.navigate(['edit']);
    const dataObj = {
      dataId: dataid,
      userId: dataUserid,
      caption: this.changeCaption,
    };
    this.userDataService
      .updateData(sessionStorage.getItem('username')!, dataObj)
      .subscribe((update) => {
        console.log('updating data');
      });

    // pass userid= sesssionStorage, dataid = , change caption
  }

  onDelete(dataid: any) {
    const dataObj = { dataId: dataid };
    console.log(dataObj.dataId);
    this.userDataService
      .deleteData(dataObj, sessionStorage.getItem('username'))
      .subscribe(() => {
        console.log('deleting');
        window.location.reload();
      });
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
