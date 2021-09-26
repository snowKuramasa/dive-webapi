package com.dive.divewebapi.requestBody;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyReadRequest {

  private Integer userId;

  private Integer messageId;


  public AlreadyReadRequest(Integer userId , Integer messageId){
    super();
    this.setUserId(userId);
    this.setMessageId(messageId);
  }

}


