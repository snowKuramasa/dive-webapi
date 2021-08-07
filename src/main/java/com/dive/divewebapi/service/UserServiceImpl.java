package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
@Autowired
UserRepository userRepository;

  @Override
  public List<TUser> getAll(){
    List<TUser> users = userRepository.findAll();
    return users;
  }

  @Override
  public Optional<TUser> getById(Integer userId) {

    Optional<TUser> user;
    //Optionalは検索して無ければnullを返す型という意味
    //TODO:Exceptionを返すようにすること

    user = userRepository.findById(userId);
    return user;
  }

  @Override
  public TUser save() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TUser delete() {
    // TODO Auto-generated method stub
    return null;
  };

  // public UserRepository getById();

  // public UserRepository save();

  // public UserRepository delete();

}
