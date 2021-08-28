package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.exception.UserNotSaveException;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

  public List<TUser> getAll() throws UserNotFoundException;

  public Optional<TUser>  getById(Integer userId) throws UserNotFoundException;

  public TUser save(TUser user) throws UserNotSaveException;

  public TUser update(TUser user);

  public TUser delete(TUser user);

}
