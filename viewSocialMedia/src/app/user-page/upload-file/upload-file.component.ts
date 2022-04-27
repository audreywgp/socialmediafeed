import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserdataService } from 'src/app/userdata.service';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css'],
})
export class UploadFileComponent implements OnInit {
  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';
  fileInfos?: Observable<any>;
  dataObj = {};
  filename: string | undefined;
  caption: string | undefined;
  username = sessionStorage.getItem('username');

  constructor(
    private userDataService: UserdataService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // this.fileInfos = this.userDataService.getFiles(); // display all files initially
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  upload(): void {
    this.progress = 0;
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      if (file) {
        console.log(this.caption);
        this.currentFile = file;
        this.userDataService.upload(this.currentFile).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round((100 * event.loaded) / event.total);
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.userDataService.getFiles(); // get all files
              // this.fileInfos = this.userDataService.getUploadFile(file.name);
              this.filename = file.name;
              // sessionStorage.setItem('filename', file.name);
              console.log('event:' + event.type);
              event.type;
              this.addUserData();
            }
          },
          (err: any) => {
            console.log(err);
            this.progress = 0;
            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }
            this.currentFile = undefined;
          }
        );
      }
      this.selectedFiles = undefined;
    }
  }

  addUserData() {
    this.dataObj = {
      userId: parseInt(sessionStorage.getItem('userId')!),
      caption: this.caption,
      postType: sessionStorage.getItem('postType'),
      locationPath: this.filename,
    };
    this.userDataService.addData(this.dataObj).subscribe((send) => {
      this.router.navigate(['user', sessionStorage.getItem('username')]);
      console.log('adding data');
    });
  }
}
