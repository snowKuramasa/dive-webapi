package com.dive.divewebapi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TAlreadyRead;
import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotSaveException;
import com.dive.divewebapi.requestBody.AlreadyReadRequest;
import com.dive.divewebapi.requestBody.UserRequest;
import com.dive.divewebapi.response.AlreadyReadResponse;
import com.dive.divewebapi.response.MessageResponseList;
import com.dive.divewebapi.response.UserResponse;
import com.dive.divewebapi.response.UserResponseList;
import com.dive.divewebapi.exception.AlreadyReadNotFoundException;
import com.dive.divewebapi.exception.AlreadyReadNotSaveException;
import com.dive.divewebapi.exception.MessageNotFoundException;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.service.AlreadyReadServiceImpl;
import com.dive.divewebapi.service.MessageServiceImpl;
import com.dive.divewebapi.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequestMapping("rest/alreadyReads")
public class AlreadyReadController {

  @Autowired
  MessageServiceImpl messageService;

  @Autowired
  UserServiceImpl userService;

  @Autowired
  AlreadyReadServiceImpl alreadyReadService;


  // /**未読メッセージ取得 */
  // @GetMapping("/notalreadyRead/message/users/{id}")
  // ResponseEntity<MessageResponseList> getAlreadyReadMessagesByUserId(@PathVariable String id) {

  //   Integer userId = Integer.parseInt(id);

  //   try {
  //     //get TAlreadyRead entity
  //     List<TAlreadyRead> alreadyReadEntityList = alreadyReadService.getByUserId(userId);

  //     //既読メッセージ
  //     List<TMessage> alreadyMessageEntityList = new ArrayList<TMessage>();

  //     //受信者が現在のユーザーIDであるメッセージ
  //     List<TMessage> receiverMessageEntityList = new ArrayList<TMessage>();

  //     //ユーザーが所属する部屋IDを持つメッセージ
  //     List<TMessage> roomMessageEntityList = new ArrayList<TMessage>();


  //     //userIdで検索したalreadyReadからTMessageを取得(既読したメッセージ)
  //     alreadyReadEntityList.forEach(alreadyReadEntity -> {
  //       alreadyMessageEntityList.add(alreadyReadEntity.getMessage());
  //     });

  //     //受信者が現在のユーザーIDであるメッセージ
  //     receiverMessageEntityList = messageService.getByReceiverId(userId);

  //     //TODO:ユーザーが所属する部屋IDを持つメッセージ
  //     //関連づいた部屋一覧取得
  //     //↑の部屋IDを使ってメッセージを検索 messageService.getByRoomId(userId);



  //     MessageResponseList messageResponseList = new MessageResponseList(messageEntityList);

  //     return ResponseEntity.ok(messageResponseList);

  //   } catch (AlreadyReadNotFoundException e) {

  //     e.setMessage("This alreadyRead not found.");
  //     System.err.println(e.getMessage());

  //     return ResponseEntity.notFound().build();
  //   }
  // }

  @PostMapping
  ResponseEntity<AlreadyReadResponse> postAlreadyRead(@RequestBody AlreadyReadRequest alreadyRead)
  throws UserNotFoundException, MessageNotFoundException {

    try {
      //JPA Entity
      TAlreadyRead saveAlreadyReadEntity = new TAlreadyRead();

      TUser alreadyReadUserEntity = userService.getById(alreadyRead.getUserId()).get();
      TMessage alreadyReadMessageEntity = messageService.getById(alreadyRead.getMessageId()).get();

      //set composite key
      saveAlreadyReadEntity.setUser(alreadyReadUserEntity);
      saveAlreadyReadEntity.setMessage(alreadyReadMessageEntity);

      TAlreadyRead savedalreadyReadEntity = alreadyReadService.save(saveAlreadyReadEntity);

      AlreadyReadResponse alreadyReadResponse = new AlreadyReadResponse(savedalreadyReadEntity);

      //Create path for saved users.
      //Expected "api/messages/{userId}"
      URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri() //->Expected api/alreadyReads
                .path("/alreadyRead/messages/{id}")                    //->Expected api/alreadyReads/{userId}
                .buildAndExpand(alreadyReadResponse.getUserId())       //->Expected insert userId
                .toUri();

      return ResponseEntity.created(uri).body(alreadyReadResponse);

    } catch (AlreadyReadNotSaveException e) {

      e.setMessage("This alreadyRead could not saved.");
      System.err.println(e.getMessage());

      return ResponseEntity.badRequest().build();
    }
  }

}
