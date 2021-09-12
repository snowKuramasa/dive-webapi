package com.dive.divewebapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotSaveException;
import com.dive.divewebapi.requestBody.UserRequest;
import com.dive.divewebapi.response.UserResponse;
import com.dive.divewebapi.response.UserResponseList;
import com.dive.divewebapi.exception.UserNotFoundException;
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
@RequestMapping("rest/users")
public class UserController {
  @Autowired
  UserServiceImpl userService;


  @GetMapping
  ResponseEntity<UserResponseList> getUsers() {

    try {
      List<TUser> userEntityList = userService.getAll();

      UserResponseList userResponseList = new UserResponseList(userEntityList);

      return ResponseEntity.ok(userResponseList);

    } catch (UserNotFoundException e) {

      e.setMessage("This user not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  ResponseEntity<UserResponse> getUsersById(@PathVariable String id) {

    Integer userId = Integer.parseInt(id);

    try {
      //get TUser entity
      TUser userEntity = userService.getById(userId).get();

      UserResponse userResponse = new UserResponse(userEntity);

      return ResponseEntity.ok(userResponse);

    } catch (UserNotFoundException e) {

      e.setMessage("This user not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  ResponseEntity<UserResponse> postUser(@RequestBody UserRequest user) {

    try {
      //JPA Entity
      TUser saveUserEntity = new TUser();

      //save request body
      saveUserEntity.setUserMail(user.getUserMail());
      saveUserEntity.setUserPassword(user.getUserPassword());
      saveUserEntity.setUserName(user.getUserName());
      saveUserEntity.setRole(user.getRole());

      TUser savedUserEntity = userService.save(saveUserEntity);

      UserResponse userResponse = new UserResponse(savedUserEntity);

      //Create path for saved users.
      //Expected "api/users/{userId}"
      URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri() //->Expected api/users
                .path("/{id}")                                      //->Expected api/users/{userId}
                .buildAndExpand(userResponse.getUserId())           //->Expected insert userId
                .toUri();

      return ResponseEntity.created(uri).body(userResponse);

    } catch (UserNotSaveException e) {

      e.setMessage("This user could not saved.");
      System.err.println(e.getMessage());

      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  ResponseEntity<UserResponse> putUser(
    @PathVariable String id,
    @RequestBody UserRequest user
  ) throws UserNotSaveException {

    Integer userId = Integer.parseInt(id);

    try {
       //get TUser entity
       TUser userEntity = userService.getById(userId).get();

      //set request body
      userEntity.setUserName(user.getUserName());
      userEntity.setUserMail(user.getUserMail());
      userEntity.setUserPassword(user.getUserPassword());
      userEntity.setUserProfile(user.getUserProfile());
      userEntity.setRole(user.getRole());

      //save entity
      TUser updatedUserEntity = userService.update(userEntity);

      UserResponse userResponse = new UserResponse(updatedUserEntity);

      return ResponseEntity.ok(userResponse);

    } catch (UserNotFoundException e) {

      e.setMessage("This user not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  ResponseEntity<UserResponse> deleteUser(@PathVariable String id) {

    Integer userId = Integer.parseInt(id);

    try {
      //get TUser entity
      TUser userEntity = userService.getById(userId).get();

      TUser deletedUserEntity = userService.delete(userEntity);

      UserResponse userResponse = new UserResponse(deletedUserEntity);

      return ResponseEntity.ok(userResponse);

    } catch (UserNotFoundException e) {

      e.setMessage("This user not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }
}
