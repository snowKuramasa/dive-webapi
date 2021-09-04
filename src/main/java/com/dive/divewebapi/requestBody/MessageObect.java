package com.dive.divewebapi.requestBody;

import java.util.Optional;

import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageObect {
  @Autowired
  UserServiceImpl userService;

  private String message;

  private Integer senderUserId;

  private Integer receiverUserId;


  public String getMessage() {
    return this.message;
  }

  public Integer getSenderUserId() {
    return this.senderUserId;
  }

  public Integer getReceiverUserId() {
    return this.receiverUserId;
  }

}


