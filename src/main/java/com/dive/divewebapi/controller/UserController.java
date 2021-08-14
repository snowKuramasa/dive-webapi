package com.dive.divewebapi.controller;

import java.util.List;

import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("rest/users")
public class UserController {
  @Autowired
  UserServiceImpl userservice;

  @GetMapping
  List<TUser> getUsers(){
      List<TUser> userEntityList = userservice.getAll();

      return userEntityList;

  }

  @PostMapping
  TUser postUser(@RequestBody TUser user) {
      TUser userEntity = userservice.save(user);
      return userEntity;

  }

}
