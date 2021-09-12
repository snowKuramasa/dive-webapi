package com.dive.divewebapi.response;

import java.util.ArrayList;
import java.util.List;

import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.entity.TUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseList {


  private List<UserResponse> userResponseList;

  public UserResponseList(List<TUser> userEntityList){

    List<UserResponse> userList = new ArrayList<UserResponse>();

    userEntityList.forEach(entity->{
      userList.add(new UserResponse(entity));
    });

    setUserResponseList(userList);
  }
}
