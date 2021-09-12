package com.dive.divewebapi.requestBody;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

  private String userMail;

  private String userPassword;

  private String userName;

  private String userProfile;

  //TODO:固定値1=admin,2=userを用意してリクエスト時にセットするように修正する
  private Integer role;

  public UserRequest(
   String  userMail,
   String  userPassword,
   String  userName,
   String  userProfile,
   Integer role
   ){
    super();
    this.setUserMail(userMail);
    this.setUserPassword(userPassword);
    this.setUserName(userName);
    this.setUserProfile(userProfile);
    this.setRole(role);
  }

}


