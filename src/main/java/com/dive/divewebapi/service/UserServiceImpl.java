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
  public List<TUser> getAll() throws UserNotFoundException {
    List<TUser> users = userRepository.findAll();

    if(users.size() == 0) throw new UserNotFoundException();

    return users;
  }

  /**
   * get by ID
   */
  @Override
  public Optional<TUser> getById(Integer userId) throws UserNotFoundException {

    Optional<TUser> user; //Optionalはnull許容型
    user = userRepository.findById(userId);

    if(user.isEmpty()) throw new UserNotFoundException();

    return user;
  }

  /**
   * save
   * @throws UserNotSaveException
   */
  @Override
  public TUser save(TUser user) throws UserNotSaveException {
    //リクエストデータにuserIdが入っているときは保存不可
    //Exception when userId is included in request data.
    if(user.getUserId() != null) throw new UserNotSaveException();
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
