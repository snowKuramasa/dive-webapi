package com.dive.divewebapi.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
  ResponseEntity<MessageResponse> getMessageById(@PathVariable String id) {

    Integer messageId = Integer.parseInt(id);

    try {
      // get TMessage entity
      Optional<TMessage> messageOptionalEntity = messageService.getById(messageId);

      TMessage getedMessageEntity = messageOptionalEntity.get();

      MessageResponse messageResponse = new MessageResponse(
                                          getedMessageEntity.getSenderUser(),
                                          getedMessageEntity.getReceiverUser(),
                                          getedMessageEntity
                                        );

      //TODO:Return response status code 201(created)
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

  @PostMapping
  ResponseEntity<MessageResponse> postMessage(@RequestBody MessageRequest message) throws UserNotFoundException {

    try {
      //JPA Entity
      TMessage saveMessageEntity = new TMessage();

      //set request body
      TUser senderUser = userService.getById(message.getSenderUserId()).get();
      TUser receiverUser = userService.getById(message.getReceiverUserId()).get();

      saveMessageEntity.setMessage(message.getMessage());
      saveMessageEntity.setSenderUser(senderUser);
      saveMessageEntity.setReceiverUser(receiverUser);

      TMessage savedMessageEntity = messageService.save(saveMessageEntity);

      MessageResponse messageResponse = new MessageResponse(
                                          savedMessageEntity.getSenderUser(),
                                          savedMessageEntity.getReceiverUser(),
                                          savedMessageEntity
                                        );

      return ResponseEntity.ok(messageResponse);

    } catch (MessageNotSaveException e) {

      e.setMessage("This message could not saved.");
      System.err.println(e.getMessage());

      return ResponseEntity.badRequest().build();
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
      TMessage updateMessageEntity = messageService.getById(messageId).get();

      //set request body
      updateMessageEntity.setMessage(message.getMessage());

      //save entity
      TMessage updatedMessageEntity = messageService.update(updateMessageEntity);


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
      Optional<TMessage> messageEntity = messageService.getById(messageId);

      TMessage deleteMessageEntity = messageEntity.get();

      TMessage deletedMessageEntity = messageService.delete(deleteMessageEntity);


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
