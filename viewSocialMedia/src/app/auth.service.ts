import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  public username: String | undefined;
  public password: String | undefined;

  constructor(private http: HttpClient) {}
  SessionAuthName = 'authenticatedUser';

  private baseUrl = 'http://localhost:9090/';

  // authenticationService(username: String, password: String) {
  //   return this.http.get(`http://localhost:8080/api/v1/basicauth`,
  //     { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res) => {
  //       this.username = username;
  //       this.password = password;
  //       this.registerSuccessfulLogin(username, password);
  //     }));
  // }
}
