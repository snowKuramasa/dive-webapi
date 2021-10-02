package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TUserRoom;
import com.dive.divewebapi.entity.TRoom;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserRoomNotFoundException;
import com.dive.divewebapi.exception.UserRoomNotSaveException;
import com.dive.divewebapi.exception.RoomNotFoundException;
import com.dive.divewebapi.exception.UserNotFoundException;

import org.springframework.stereotype.Service;

@Service
public interface UserRoomService {

  public List<TUserRoom> getAll() throws UserRoomNotFoundException;

  public List<TUserRoom> getByUserId(Integer userId) throws UserRoomNotFoundException;

  public List<TUserRoom> getByRoomId(Integer messageId) throws UserRoomNotFoundException;

  public Optional<TUserRoom> getByUserIdRoomId(Integer userId, Integer messageId)
    throws UserRoomNotFoundException , UserNotFoundException , RoomNotFoundException;

  public TUserRoom save(TUserRoom favorite) throws UserRoomNotSaveException;

  public TUserRoom update(TUserRoom userRoom);

  public TUserRoom delete(TUserRoom favorite);

}
