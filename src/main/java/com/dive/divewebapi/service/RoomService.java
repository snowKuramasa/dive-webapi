package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TRoom;
import com.dive.divewebapi.exception.RoomNotFoundException;
import com.dive.divewebapi.exception.RoomNotSaveException;

import org.springframework.stereotype.Service;

@Service
public interface RoomService {

  public List<TRoom> getAll() throws RoomNotFoundException;

  public Optional<TRoom>  getById(Integer messageId) throws RoomNotFoundException;

  public TRoom save(TRoom message) throws RoomNotSaveException;

  public TRoom update(TRoom message);

  public TRoom delete(TRoom message);

  public List<TRoom> getByCreaterId(Integer createrId) throws RoomNotFoundException;

  public List<TRoom> getByName(Integer createrId) throws RoomNotFoundException;

  public List<TRoom> getByThumbnailId(Integer thumbnailId) throws RoomNotFoundException;


}
