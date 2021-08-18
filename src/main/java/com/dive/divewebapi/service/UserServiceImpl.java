package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotSaveException;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
@Autowired
UserRepository userRepository;

  /**
   * get all users
   */
  @Override
  public List<TUser> getAll(){
    List<TUser> users = userRepository.findAll();
    return users;
  }

  /**
   * get by ID
   */
  @Override
  public Optional<TUser> getById(Integer userId) {

    Optional<TUser> user; //Optionalは検索して無ければnullを返す
    user = userRepository.findById(userId);
    return user;
  }

  /**
   * save
   * @throws UserNotSaveException
   */
  @Override
  public TUser save(TUser user) throws UserNotSaveException {
    //リクエストデータにuserIdが入っているときは例外
    //Exception when userId is included in request data.
    if(user.getUserId() == null) throw new UserNotSaveException();
    return userRepository.save(user);
  }

  /**
   * update
   */
  @Override
  public TUser update(TUser user) {
    return userRepository.save(user);
  }

  /**
   * logical delete
   */
  @Override
  public TUser delete(TUser user) {
    int deleteFrag = 1;
    user.setDeleted(deleteFrag);
    return userRepository.save(user);
  };
}
