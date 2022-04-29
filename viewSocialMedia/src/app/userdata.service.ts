import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  HttpClient,
  HttpEvent,
  HttpHeaders,
  HttpRequest,
} from '@angular/common/http';
import { UserData } from './user-data';

@Injectable({
  providedIn: 'root',
})
export class UserdataService {
  private baseUrl = 'http://localhost:9090/data';

  constructor(private http: HttpClient) {}

  getUserdata(user_id: number | undefined): Observable<UserData[]> {
    return this.http.get<UserData[]>(`${this.baseUrl}/get/${user_id}`);
  }

  // getAllUserdata() {
  //   let authusername = 'ben';
  //   let password = 'ben123';
  //   const headers = new HttpHeaders({
  //     // Basic <Base64 encoded username and password>
  //     Authorization: 'Basic ' + btoa(`$${authusername}:${password}`),
  //   });
  //   return this.http.get<UserData[]>(`${this.baseUrl}/all`);
  // }

  getAllUserdata(): Observable<UserData[]> {
    return this.http.get<UserData[]>(`${this.baseUrl}/all`);
  }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest(
      'POST',
      `http://localhost:9090/files/upload`,
      formData,
      {
        reportProgress: true,
        responseType: 'json',
      }
    );
    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(`http://localhost:9090/files/all`);
  }
  getUploadFile(filename: String): Observable<any> {
    console.log(`http://localhost:9090/files/${filename}`);
    return this.http.get(`http://localhost:9090/files/${filename}`);
  }

  addData(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add`, user);
  }
  updateData(username: string, data: Object): Observable<Object> {
    return this.http.post(
      `${this.baseUrl}/${sessionStorage.getItem('username')}/update`,
      data
    );
  }

  deleteData(dataId: Object, username: string | null) {
    return this.http.post(
      `${this.baseUrl}/${sessionStorage.getItem('username')}/delete`,
      dataId
    );
  }

  // getAccountByUsername(username : string | undefined) : Observable<Account>{

  //   return this.httpClient.get<Account>(`${this.baseUrl}/getAccountUsername/${username}`);

  // }
}
