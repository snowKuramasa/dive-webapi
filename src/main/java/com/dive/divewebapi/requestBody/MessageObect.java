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

  private Optional<TUser> senderUser;

  private Integer receiverUserId;

  private Optional<TUser> receiverUser;

  public MessageObect() throws UserNotFoundException{
    this.senderUser   = userService.getById(senderUserId);
    this.receiverUser = userService.getById(receiverUserId);
  }

}


