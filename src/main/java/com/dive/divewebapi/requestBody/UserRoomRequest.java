package com.dive.divewebapi.requestBody;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoomRequest {

  private Integer userId;

  private Integer roomId;


  public UserRoomRequest(Integer userId , Integer roomId){
    super();
    this.setUserId(userId);
    this.setRoomId(roomId);
  }

}


