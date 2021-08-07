package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TUser;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

  public List<TUser> getAll();

  public Optional<TUser>  getById(Integer userId);

  public TUser save();

  public TUser delete();

}
