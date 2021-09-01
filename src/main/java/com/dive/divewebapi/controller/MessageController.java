package com.dive.divewebapi.controller;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.exception.MessageNotSaveException;
import com.dive.divewebapi.requestBody.MessageObect;
import com.dive.divewebapi.exception.MessageNotFoundException;
import com.dive.divewebapi.service.MessageServiceImpl;
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
  MessageServiceImpl messageservice;


  @GetMapping
  ResponseEntity<List<TMessage>> getMessages() {

    try {
      List<TMessage> messageEntityList = messageservice.getAll();

      return ResponseEntity.ok(messageEntityList);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  ResponseEntity<Optional<TMessage>> getMessageById(@PathVariable String id) {

    Integer messageId = Integer.parseInt(id);

    try {
      // get TMessage entity
      Optional<TMessage> messageEntity = messageservice.getById(messageId);

      //TODO:Return response status code 201(created)
      return ResponseEntity.ok(messageEntity);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/sender/{id}")
  ResponseEntity<List<TMessage>> getMessagesBySenderId(@PathVariable String id) {

    Integer senderId = Integer.parseInt(id);

    try {
      List<TMessage> messageEntityList = messageservice.getBySenderId(senderId);

      return ResponseEntity.ok(messageEntityList);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/receiver/{id}")
  ResponseEntity<List<TMessage>> getMessagesByReceiverId(@PathVariable String id) {

    Integer receiverId = Integer.parseInt(id);

    try {
      List<TMessage> messageEntityList = messageservice.getByReceiverId(receiverId);

      return ResponseEntity.ok(messageEntityList);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  ResponseEntity<TMessage> postMessage(@RequestBody MessageObect message) {

    try {
      //JPA Entity
      TMessage saveMessageEntity = new TMessage();

      //copy request body property to entity
      BeanUtils.copyProperties(saveMessageEntity, message);

      TMessage savedMessageEntity = messageservice.save(saveMessageEntity);

      return ResponseEntity.ok(savedMessageEntity);

    } catch (MessageNotSaveException e) {

      e.setMessage("This message could not saved.");
      System.err.println(e.getMessage());

      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  ResponseEntity<TMessage> putMessage(
    @PathVariable String id,
    @RequestBody TMessage message
  ) throws MessageNotSaveException {

    Integer messageId = Integer.parseInt(id);

    try {
      Optional<TMessage> messageEntity = messageservice.getById(messageId);

      //get TMessage entity
      TMessage updateMessageEntity = messageEntity.get();

      //set request body
      updateMessageEntity.setMessage(message.getMessage());

      //save entity
      TMessage updatedMessage = messageservice.update(updateMessageEntity);

      return ResponseEntity.ok(updatedMessage);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  ResponseEntity<TMessage> deleteMessage(@PathVariable String id) {

    Integer messageId = Integer.parseInt(id);

    try {
      Optional<TMessage> messageEntity = messageservice.getById(messageId);

      TMessage deleteMessageEntity = messageEntity.get();

      TMessage deletedMessageEntity = messageservice.delete(deleteMessageEntity);

      return ResponseEntity.ok(deletedMessageEntity);

    } catch (MessageNotFoundException e) {

      e.setMessage("This message not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }
}
