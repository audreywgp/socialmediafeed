import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { UserData } from '../user-data';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css'],
})
export class PostComponent implements OnInit {
  @Input() onepost: UserData | any;
  @Input() username: string | undefined;
  @Input() time: any;

  constructor() {}

  ngOnInit(): void {}

  autoplay = false;
  controls = false;

  onview() {
    this.autoplay = true;
    this.controls = true;
  }

  // onEdit(dataid: any, dataUserid: any) {
  //   // this.router.navigate(['edit']);
  //   const dataObj = {
  //     dataId: dataid,
  //     userId: dataUserid,
  //     caption: this.changeCaption,
  //   };
  //   this.userDataService
  //     .updateData(sessionStorage.getItem('username')!, dataObj)
  //     .subscribe((update) => {
  //       console.log('updating data');
  //     });

  //   // pass userid= sesssionStorage, dataid = , change caption
  // }

  // onDelete(dataid: any) {
  //   const dataObj = { dataId: dataid };
  //   console.log(dataObj.dataId);
  //   this.userDataService
  //     .deleteData(dataObj, sessionStorage.getItem('username'))
  //     .subscribe(() => {
  //       console.log('deleting');
  //       window.location.reload();
  //     });
  // }
}
