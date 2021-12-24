import { Component, OnInit } from '@angular/core';

import { User } from 'src/app/model/user/user.model';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  users?: User[];
  user = new User();
  address?: any;
  error?: boolean;
  errorText?: string;
  success?: boolean;
  successText?: string;
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  regexp?: any;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.error = false;
    this.success = false;
    this.retrieveUser();
  }

  saveUser(name: string, surname: string, email: string, phone: string, address: string) {

    this.user.firstname = name;
    this.user.lastname = surname;
    this.user.email = email;
    this.user.phone = phone;
    this.user.address = address;

    if (name == "" || surname == "" || email == "") {
      this.error = true;
      this.success = false;
      this.errorText = "** Please check the error details";
      console.log("Error");
    } else {
      if (!this.isEmail(email)) {
        this.error = true;
        this.success = false;
        this.errorText = "** Please Check Email Format";
      } else {
        this.sendUser(this.user);
      }

    }

  }

  isEmail(search: string): boolean {
    var serchfind: boolean;

    this.regexp = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);

    serchfind = this.regexp.test(search);
    return serchfind
  }

  sendUser(user: User) {

    this.userService.validateEmail(user.email)
      .subscribe(
        data => {
          if (!data) {
            console.log(data);
            this.userService.create(user)
              .subscribe(
                data => {
                  console.log(data);
                  this.error = false;
                  this.success = true;
                  this.successText = user.email + " saved successfully";

                },
                error => {
                  console.log(error);
                });
          } else {
            this.error = true;
            this.success = false;
            this.errorText = "** Email Already Exist";
          }
        },
        error => {
          console.log(error);
        });


  }

  retrieveUser(): void {
    this.userService.getAll()
      .subscribe(
        data => {
          this.users = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

}
