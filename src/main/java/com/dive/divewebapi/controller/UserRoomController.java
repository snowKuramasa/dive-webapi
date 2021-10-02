package com.dive.divewebapi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TUserRoom;
import com.dive.divewebapi.entity.TRoom;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotSaveException;
import com.dive.divewebapi.requestBody.UserRoomRequest;
import com.dive.divewebapi.requestBody.UserRequest;
import com.dive.divewebapi.response.UserRoomResponse;
import com.dive.divewebapi.response.RoomResponseList;
import com.dive.divewebapi.response.UserResponse;
import com.dive.divewebapi.response.UserResponseList;
import com.dive.divewebapi.exception.UserRoomNotFoundException;
import com.dive.divewebapi.exception.UserRoomNotSaveException;
import com.dive.divewebapi.exception.RoomNotFoundException;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.service.UserRoomServiceImpl;
import com.dive.divewebapi.service.RoomServiceImpl;
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
@RequestMapping("rest/userroom")
public class UserRoomController {

  @Autowired
  RoomServiceImpl roomService;

  @Autowired
  UserServiceImpl userService;

  @Autowired
  UserRoomServiceImpl userRoomService;



  @GetMapping("/user/{id}")
  ResponseEntity<RoomResponseList> getUserRoomRoomsByUserId(@PathVariable String id) {

    Integer userId = Integer.parseInt(id);

    try {
      //get TUserRoom entity
      List<TUserRoom> userRoomEntityList = userRoomService.getByUserId(userId);

      List<TRoom> roomEntityList = new ArrayList<TRoom>();

      //userIdで検索したuserRoomからTRoomを取得
      userRoomEntityList.forEach(userRoomEntity -> {
        roomEntityList.add(userRoomEntity.getRoom());
      });

      RoomResponseList roomResponseList = new RoomResponseList(roomEntityList);

      return ResponseEntity.ok(roomResponseList);

    } catch (UserRoomNotFoundException e) {

      e.setMessage("This userRoom not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/room/{id}")
  ResponseEntity<UserResponseList> getUserRoomUsersByRoomId(@PathVariable String id) {

    Integer roomId = Integer.parseInt(id);

    try {
      //get TUserRoom entity
      List<TUserRoom> favoriteEntityList = userRoomService.getByRoomId(roomId);

      List<TUser> userEntityList = new ArrayList<TUser>();

      //userIdで検索したuserRoomからTUserを取得
      favoriteEntityList.forEach(favoriteEntity -> {
        userEntityList.add(favoriteEntity.getUser());
      });

      UserResponseList userResponseList = new UserResponseList(userEntityList);

      return ResponseEntity.ok(userResponseList);

    } catch (UserRoomNotFoundException e) {

      e.setMessage("This userRoom not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  ResponseEntity<UserRoomResponse> postUserRoom(@RequestBody UserRoomRequest userRoom)
  throws UserNotFoundException, RoomNotFoundException {

    try {
      //JPA Entity
      TUserRoom saveUserRoomEntity = new TUserRoom();

      TUser userRoomUserEntity = userService.getById(userRoom.getUserId()).get();
      TRoom userRoomRoomEntity = roomService.getById(userRoom.getRoomId()).get();

      //set composite key
      saveUserRoomEntity.setUser(userRoomUserEntity);
      saveUserRoomEntity.setRoom(userRoomRoomEntity);
      saveUserRoomEntity.setVerify(0);

      TUserRoom savedUserRoomEntity = userRoomService.save(saveUserRoomEntity);

      UserRoomResponse userRoomResponse = new UserRoomResponse(savedUserRoomEntity);

      //Create path for saved users.
      //Expected "api/rooms/{userId}"
      URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri() //->Expected api/userroom
                .path("/userroom/user/{id}")                    //->Expected api/userroom/{userId}
                .buildAndExpand(userRoomResponse.getUserId())     //->Expected insert userId
                .toUri();

      return ResponseEntity.created(uri).body(userRoomResponse);

    } catch (UserRoomNotSaveException e) {

      e.setMessage("This userRoom could not saved.");
      System.err.println(e.getMessage());

      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping
  ResponseEntity<UserRoomResponse> putUserRoom(@RequestBody UserRoomRequest userRoom)
  throws UserNotFoundException, RoomNotFoundException {

    try {
      //JPA Entity
      TUserRoom userRoomEntity;
      try {
        userRoomEntity = userRoomService.getByUserIdRoomId(
                                          userRoom.getUserId(),
                                          userRoom.getRoomId()
                                        ).get();

      } catch (UserRoomNotFoundException e) {
        e.setMessage("This userRoom not found.");
        System.err.println(e.getMessage());
        return ResponseEntity.notFound().build();
      }

      //set composite key
      userRoomEntity.setVerify(1);

      TUserRoom updatedUserRoomEntity = userRoomService.save(userRoomEntity);

      UserRoomResponse userRoomResponse = new UserRoomResponse(updatedUserRoomEntity);

      return ResponseEntity.ok(userRoomResponse);

    } catch (UserRoomNotSaveException e) {

      e.setMessage("This userRoom could not saved.");
      System.err.println(e.getMessage());

      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping
  ResponseEntity<UserRoomResponse> deleteUserRoom(@RequestBody UserRoomRequest userRoom)
  throws UserNotFoundException, RoomNotFoundException {

    try {
      //JPA Entity
      Integer userRoomUserId = userService.getById(userRoom.getUserId()).get().getUserId();
      Integer userRoomRoomId = roomService.getById(userRoom.getRoomId()).get().getRoomId();

      TUserRoom userRoomEntity = userRoomService.getByUserIdRoomId(userRoomUserId,userRoomRoomId).get();

      TUserRoom deletedUserRoomEntity = userRoomService.delete(userRoomEntity);

      UserRoomResponse userRoomResponse = new UserRoomResponse(deletedUserRoomEntity);

      return ResponseEntity.ok(userRoomResponse);

    } catch (UserRoomNotFoundException e) {

      e.setMessage("This userRoom not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.badRequest().build();
    }
  }
}
