package com.dive.divewebapi.requestBody;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {

  private String message;

  private Integer senderUserId;

  private Integer receiverUserId;


  public MessageRequest(String message, Integer senderUserId, Integer receiverUser){
    super();
    this.setMessage(message);
    this.setSenderUserId(senderUserId);
    this.setReceiverUserId(receiverUserId);
  }

}


