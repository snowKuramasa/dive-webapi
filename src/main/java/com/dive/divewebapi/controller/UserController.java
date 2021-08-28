package com.dive.divewebapi.controller;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotSaveException;
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



@RestController
@RequestMapping("rest/users")
public class UserController {
  @Autowired
  UserServiceImpl userservice;


  @GetMapping
  ResponseEntity<List<TUser>> getUsers() {

    try {
      List<TUser> userEntityList = userservice.getAll();

      return ResponseEntity.ok(userEntityList);

    } catch (UserNotFoundException e) {

      e.setMessage("This user not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  ResponseEntity<Optional<TUser>> getUserById(@PathVariable String id) {

    Integer userId = Integer.parseInt(id);

    try {
      // get TUser entity
      Optional<TUser> userEntity = userservice.getById(userId);

      //TODO:Return response status code 201(created)
      return ResponseEntity.ok(userEntity);

    } catch (UserNotFoundException e) {

      e.setMessage("This user not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  ResponseEntity<TUser> postUser(@RequestBody TUser user) {

    try {

      //save request body
      TUser savedUserEntity = userservice.save(user);
      return ResponseEntity.ok(savedUserEntity);

    } catch (UserNotSaveException e) {

      e.setMessage("This user could not saved.");
      System.err.println(e.getMessage());

      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  ResponseEntity<TUser> putUser(
    @PathVariable String id,
    @RequestBody TUser user
  ) throws UserNotSaveException {

    Integer userId = Integer.parseInt(id);

    try {
      Optional<TUser> userEntity = userservice.getById(userId);

      //get TUser entity
      TUser updateUserEntity = userEntity.get();

      //set request body
      updateUserEntity.setUserName(user.getUserName());
      updateUserEntity.setUserMail(user.getUserMail());
      updateUserEntity.setUserPassword(user.getUserPassword());
      updateUserEntity.setUserProfile(user.getUserProfile());

      //save entity
      TUser updatedUser = userservice.update(updateUserEntity);

      return ResponseEntity.ok(updatedUser);

    } catch (UserNotFoundException e) {

      e.setMessage("This user not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  ResponseEntity<TUser> deleteUser(@PathVariable String id) {

    Integer userId = Integer.parseInt(id);

    try {
      Optional<TUser> userEntity = userservice.getById(userId);

      TUser deleteUserEntity = userEntity.get();

      TUser deletedUserEntity = userservice.delete(deleteUserEntity);

      return ResponseEntity.ok(deletedUserEntity);

    } catch (UserNotFoundException e) {

      e.setMessage("This user not found.");
      System.err.println(e.getMessage());

      return ResponseEntity.notFound().build();
    }
  }
}
