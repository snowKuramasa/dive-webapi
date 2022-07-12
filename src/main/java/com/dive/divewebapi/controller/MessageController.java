package com.dive.divewebapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.MessageNotSaveException;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.requestBody.MessageRequest;
import com.dive.divewebapi.response.MessageResponse;
import com.dive.divewebapi.response.MessageResponseList;
import com.dive.divewebapi.exception.MessageNotFoundException;
import com.dive.divewebapi.service.MessageServiceImpl;
import com.dive.divewebapi.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
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
@RequestMapping("rest/messages")
public class MessageController {
  @Autowired
  MessageServiceImpl messageService;

  @Autowired
  UserServiceImpl userService;


  @GetMapping
  ResponseEntity<MessageResponseList> getMessages() {

    try {
      List<TMessage> messageEntityList = messageService.getAll();

      MessageResponseList messageResponseList = new MessageResponseList(messageEntityList);

      return ResponseEntity.ok(messageResponseList);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  ResponseEntity<MessageResponse> getMessagesById(@PathVariable String id) {

    Integer messageId = Integer.parseInt(id);

    try {
      //get TMessage entity
      TMessage messageEntity = messageService.getById(messageId).get();

      MessageResponse messageResponse = new MessageResponse(
                                          messageEntity.getSenderUser(),
                                          messageEntity.getReceiverUser(),
                                          messageEntity
                                        );

      return ResponseEntity.ok(messageResponse);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/sender/{id}")
  ResponseEntity<MessageResponseList> getMessagesBySenderId(@PathVariable String id) {

    Integer senderId = Integer.parseInt(id);

    try {
      List<TMessage> messageEntityList = messageService.getBySenderId(senderId);

      MessageResponseList messageResponseList = new MessageResponseList(messageEntityList);

      return ResponseEntity.ok(messageResponseList);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/receiver/{id}")
  ResponseEntity<MessageResponseList> getMessagesByReceiverId(@PathVariable String id) {

    Integer receiverId = Integer.parseInt(id);

    try {
      List<TMessage> messageEntityList = messageService.getByReceiverId(receiverId);

      MessageResponseList messageResponseList = new MessageResponseList(messageEntityList);

      return ResponseEntity.ok(messageResponseList);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @MessageMapping("/users/{receiverUserId}") //クライアントからメッセージが宛先に送信された場合に、メソッドが呼び出されることを保証する。
  @SendTo("/queue/users/{receiverUserId}") //個別にユーザーへ送信するための宛先
  MessageResponse sendMessage(@RequestBody MessageRequest message) throws UserNotFoundException {

    // MessageResponse responseEntity = this.postMessage(message);
    MessageResponse messageResponse = this.postMessage(message);

    // MessageResponse messageResponse = responseEntity.getBody();

    // //メッセージが新規作成に成功した場合はメッセージを返す
    // if(responseEntity.getStatusCode() != null && responseEntity.getStatusCode().value() == 201){
    //   messageResponse = responseEntity.getBody();
    // }else{
    //   //メッセージを新規作成に失敗した場合は代わりのメッセージを返す
    //   messageResponse.setMessage("送信に失敗しました。");
    // }

    return messageResponse;
  }

  @PostMapping
  MessageResponse postMessage(@RequestBody MessageRequest message) throws UserNotFoundException {

    try {
      //JPA Entity
      TMessage messageEntity = new TMessage();

      //set request body
      TUser senderUser = userService.getById(message.getSenderUserId()).get();
      TUser receiverUser = userService.getById(message.getReceiverUserId()).get();

      messageEntity.setMessage(message.getMessage());
      messageEntity.setSenderUser(senderUser);
      messageEntity.setReceiverUser(receiverUser);
      //TODO:TMessageのbelong_to_room_idの処理も入れること（roomIdに指定があるときはチャット、無い時はDMとして保存する）

      TMessage savedMessageEntity = messageService.save(messageEntity);

      MessageResponse messageResponse = new MessageResponse(
                                          savedMessageEntity.getSenderUser(),
                                          savedMessageEntity.getReceiverUser(),
                                          savedMessageEntity
                                        );

      //Create path for saved messages.
      //Expected "api/messages/{messageId}"
      // URI uri = ServletUriComponentsBuilder
      //           .fromCurrentRequestUri() //->Expected api/messages
      //           .path("/{id}")                                      //->Expected api/messages/{messageId}
      //           .buildAndExpand(messageResponse.getMessageId())     //->Expected insert messageId
      //           .toUri();

      // return ResponseEntity.created(uri).body(messageResponse);
      return messageResponse;

    } catch (MessageNotSaveException e) {

      e.setMessage("This message could not saved.");
      System.err.println(e.getMessage());

      // return ResponseEntity.badRequest().build();
      //set request body
      TUser senderUser = userService.getById(message.getSenderUserId()).get();
      TUser receiverUser = userService.getById(message.getReceiverUserId()).get();

      TMessage messageEntity = new TMessage();
      messageEntity.setMessage("送信に失敗しました。");
      messageEntity.setSenderUser(senderUser);
      messageEntity.setReceiverUser(receiverUser);
      //TODO:TMessageのbelong_to_room_idの処理も入れること（roomIdに指定があるときはチャット、無い時はDMとして保存する）

      MessageResponse messageResponse = new MessageResponse(
        senderUser,
        receiverUser,
        messageEntity
      );
      return messageResponse;
    }
  }

  @PutMapping("/{id}")
  ResponseEntity<MessageResponse> putMessage(
    @PathVariable String id,
    @RequestBody MessageRequest message
  ) throws MessageNotSaveException {

    Integer messageId = Integer.parseInt(id);

    try {

      //get TMessage entity
      TMessage messageEntity = messageService.getById(messageId).get();

      //set request body
      messageEntity.setMessage(message.getMessage());

      //save entity
      TMessage updatedMessageEntity = messageService.update(messageEntity);


      MessageResponse messageResponse = new MessageResponse(
                                          updatedMessageEntity.getSenderUser(),
                                          updatedMessageEntity.getReceiverUser(),
                                          updatedMessageEntity
                                        );

      return ResponseEntity.ok(messageResponse);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  ResponseEntity<MessageResponse> deleteMessage(@PathVariable String id) {

    Integer messageId = Integer.parseInt(id);

    try {
      //get TMessage entity
      TMessage messageEntity = messageService.getById(messageId).get();

      TMessage deletedMessageEntity = messageService.delete(messageEntity);


      MessageResponse messageResponse = new MessageResponse(
                                          deletedMessageEntity.getSenderUser(),
                                          deletedMessageEntity.getReceiverUser(),
                                          deletedMessageEntity
                                        );

      return ResponseEntity.ok(messageResponse);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }
}
