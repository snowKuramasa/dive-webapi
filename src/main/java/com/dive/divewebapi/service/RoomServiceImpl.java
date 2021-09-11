package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TRoom;
import com.dive.divewebapi.exception.RoomNotFoundException;
import com.dive.divewebapi.exception.RoomNotSaveException;
import com.dive.divewebapi.repository.RoomRepository;
import com.dive.divewebapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService{
@Autowired
RoomRepository roomRepository;

UserRepository userRepository;

  /**
   * get all rooms
   */
  @Override
  public List<TRoom> getAll() throws RoomNotFoundException {
    List<TRoom> rooms = roomRepository.findAll();

    if(rooms.size() == 0) throw new RoomNotFoundException();

    return rooms;
  }

  /**
   * get by ID
   */
  @Override
  public Optional<TRoom> getById(Integer roomId) throws RoomNotFoundException {

    Optional<TRoom> room; //Optionalはnull許容型
    room = roomRepository.findById(roomId);

    if(room.isEmpty()) throw new RoomNotFoundException();

    return room;
  }

  /**
   * save
   * @throws RoomNotSaveException
   */
  @Override
  public TRoom save(TRoom room) throws RoomNotSaveException {
    //リクエストデータにroomIdが入っているときは保存不可
    //Exception when roomId is included in request data.
    if(room.getRoomId() != null) throw new RoomNotSaveException();
    return roomRepository.save(room);
  }

  /**
   * update
   */
  @Override
  public TRoom update(TRoom room) {
    return roomRepository.save(room);
  }

  /**
   *  delete
   */
  @Override
  public TRoom delete(TRoom room) {
     roomRepository.delete(room);
     return room;
  };

  /**
   * get roomCreater rooms
   */
  @Override
  public List<TRoom> getByRoomCreaterId(Integer roomCreaterId) throws RoomNotFoundException {

    List<TRoom> rooms = roomRepository.findByRoomCreaterUserId(roomCreaterId);

    if(rooms.size() == 0) throw new RoomNotFoundException();

    return rooms;
  }

  /**
   * get roomName rooms
   */
  @Override
  public List<TRoom> getByRoomName(String roomName) throws RoomNotFoundException {

    List<TRoom> rooms = roomRepository.findByRoomName(roomName);

    if(rooms.size() == 0) throw new RoomNotFoundException();

    return rooms;
  }

  /**
   * get thumbnailId rooms
   */
  @Override
  public List<TRoom> getByThumbnailId(Integer thumbnailId) throws RoomNotFoundException {

    List<TRoom> rooms = roomRepository.findByRoomCreaterUserId(thumbnailId);

    if(rooms.size() == 0) throw new RoomNotFoundException();

    return rooms;
  }

}
