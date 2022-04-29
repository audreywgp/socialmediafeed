import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserDetails } from './user-details';

@Injectable({
  providedIn: 'root',
})
export class UserdetailsService {
  private baseUrl = 'http://localhost:9090/user';

  //get the last emitted value
  mySubjectID = new BehaviorSubject<any>('none');
  mySubjectUsername = new BehaviorSubject<any>('none');

  constructor(private http: HttpClient) {}

  getAllUsers(): Observable<UserDetails[]> {
    return this.http.get<UserDetails[]>(`${this.baseUrl}/all`);
  }

  getUser(username: string | undefined): Observable<UserDetails> {
    console.log(`${this.baseUrl}/user/${username}`);
    return this.http.get<UserDetails>(`${this.baseUrl}/login/${username}`);
  }

  getUserById(id: number | undefined): Observable<UserDetails> {
    console.log(`${this.baseUrl}/user/${id}`);
    return this.http.get<UserDetails>(`${this.baseUrl}/get/${id}`);
  }

  addUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/adduser`, user);
  }

  sendUserId(userId: number) {
    //  console.log("sending userId to my subject "+userId)
    this.mySubjectID.next(userId);
  }

  passedUser(): Observable<any> {
    // console.log("recieved from my subject userid")
    // console.log(this.mySubject.asObservable());

    return this.mySubjectID.asObservable();
  }

  sendUsername(username: string) {
    this.mySubjectUsername.next(username);
  }

  passedUsername(): Observable<any> {
    return this.mySubjectUsername.asObservable();
  }
}
