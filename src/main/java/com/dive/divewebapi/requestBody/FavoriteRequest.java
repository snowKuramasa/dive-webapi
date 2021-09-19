package com.dive.divewebapi.requestBody;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteRequest {

  private Integer userId;

  private Integer messageId;


  public FavoriteRequest(Integer userId , Integer messageId){
    super();
    this.setUserId(userId);
    this.setMessageId(messageId);
  }

}


