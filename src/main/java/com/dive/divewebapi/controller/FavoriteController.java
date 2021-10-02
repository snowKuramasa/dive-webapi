package com.dive.divewebapi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TFavorite;
import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotSaveException;
import com.dive.divewebapi.requestBody.FavoriteRequest;
import com.dive.divewebapi.requestBody.UserRequest;
import com.dive.divewebapi.response.FavoriteResponse;
import com.dive.divewebapi.response.MessageResponseList;
import com.dive.divewebapi.response.UserResponse;
import com.dive.divewebapi.response.UserResponseList;
import com.dive.divewebapi.exception.FavoriteNotFoundException;
import com.dive.divewebapi.exception.FavoriteNotSaveException;
import com.dive.divewebapi.exception.MessageNotFoundException;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.service.FavoriteServiceImpl;
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
@RequestMapping("rest/favorite")
public class FavoriteController {

  @Autowired
  MessageServiceImpl messageService;

  @Autowired
  UserServiceImpl userService;

  @Autowired
  FavoriteServiceImpl favoriteService;



  @GetMapping("/user/{id}")
  ResponseEntity<MessageResponseList> getFavoriteMessagesByUserId(@PathVariable String id) {

    Integer userId = Integer.parseInt(id);

    try {
      //get TFavorite entity
      List<TFavorite> favoriteEntityList = favoriteService.getByUserId(userId);

      List<TMessage> messageEntityList = new ArrayList<TMessage>();

      //userIdで検索したfavoriteからTMessageを取得
      favoriteEntityList.forEach(favoriteEntity -> {
        messageEntityList.add(favoriteEntity.getMessage());
      });

      MessageResponseList messageResponseList = new MessageResponseList(messageEntityList);

      return ResponseEntity.ok(messageResponseList);

    } catch (FavoriteNotFoundException e) {

      e.setMessage("This favorite not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/message/{id}")
  ResponseEntity<UserResponseList> getFavoriteUsersByMessageId(@PathVariable String id) {

    Integer messageId = Integer.parseInt(id);

    try {
      //get TFavorite entity
      List<TFavorite> favoriteEntityList = favoriteService.getByMessageId(messageId);

      List<TUser> userEntityList = new ArrayList<TUser>();

      //userIdで検索したfavoriteからTUserを取得
      favoriteEntityList.forEach(favoriteEntity -> {
        userEntityList.add(favoriteEntity.getUser());
      });

      UserResponseList userResponseList = new UserResponseList(userEntityList);

      return ResponseEntity.ok(userResponseList);

    } catch (FavoriteNotFoundException e) {

      e.setMessage("This favorite not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  ResponseEntity<FavoriteResponse> postFavorite(@RequestBody FavoriteRequest favorite)
  throws UserNotFoundException, MessageNotFoundException {

    try {
      //JPA Entity
      TFavorite saveFavoriteEntity = new TFavorite();

      TUser favoriteUserEntity = userService.getById(favorite.getUserId()).get();
      TMessage favoriteMessageEntity = messageService.getById(favorite.getMessageId()).get();

      //set composite key
      saveFavoriteEntity.setUser(favoriteUserEntity);
      saveFavoriteEntity.setMessage(favoriteMessageEntity);

      TFavorite savedfavoriteEntity = favoriteService.save(saveFavoriteEntity);

      FavoriteResponse favoriteResponse = new FavoriteResponse(savedfavoriteEntity);

      //Create path for saved users.
      //Expected "api/messages/{userId}"
      URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri() //->Expected api/favorite
                .path("/favorite/user/{id}")                    //->Expected api/favorite/{userId}
                .buildAndExpand(favoriteResponse.getUserId())       //->Expected insert userId
                .toUri();

      return ResponseEntity.created(uri).body(favoriteResponse);

    } catch (FavoriteNotSaveException e) {

      e.setMessage("This favorite could not saved.");
      System.err.println(e.getMessage());

      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping
  ResponseEntity<FavoriteResponse> deleteFavorite(@RequestBody FavoriteRequest favorite)
  throws UserNotFoundException, MessageNotFoundException {

    try {
      //JPA Entity
      Integer favoriteUserId = userService.getById(favorite.getUserId()).get().getUserId();
      Integer favoriteMessageId = messageService.getById(favorite.getMessageId()).get().getMessageId();

      TFavorite favoriteEntity = favoriteService.getByUserIdMessageId(favoriteUserId,favoriteMessageId).get();

      TFavorite deletedFavoriteEntity = favoriteService.delete(favoriteEntity);

      FavoriteResponse favoriteResponse = new FavoriteResponse(deletedFavoriteEntity);

      return ResponseEntity.ok(favoriteResponse);

    } catch (FavoriteNotFoundException e) {

      e.setMessage("This favorite not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.badRequest().build();
    }
  }
}
