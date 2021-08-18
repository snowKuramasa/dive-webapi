package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotSaveException;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

  public List<TUser> getAll();

  public Optional<TUser>  getById(Integer userId);

  public TUser save(TUser user) throws UserNotSaveException;

  public TUser update(TUser user);

  public TUser delete(TUser user);

}
