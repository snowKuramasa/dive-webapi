package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TUserRoom;
import com.dive.divewebapi.entity.TRoom;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.entity.id.UserRoomRelationId;
import com.dive.divewebapi.exception.UserRoomNotFoundException;
import com.dive.divewebapi.exception.UserRoomNotSaveException;
import com.dive.divewebapi.exception.RoomNotFoundException;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.repository.UserRoomRepository;
import com.dive.divewebapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoomServiceImpl implements UserRoomService{
@Autowired
UserRoomRepository userRoomRepository;

@Autowired
UserRepository userRepository;

@Autowired
RoomServiceImpl roomService;

@Autowired
UserServiceImpl userService;


  /**
   * get all userRooms
   */
  @Override
  public List<TUserRoom> getAll() throws UserRoomNotFoundException {
    List<TUserRoom> userRooms = userRoomRepository.findAll();

    if(userRooms.size() == 0) throw new UserRoomNotFoundException();

    return userRooms;
  }

  /**
   * get by user id
   */
  @Override
  public List<TUserRoom> getByUserId(Integer userId) throws UserRoomNotFoundException {


    List<TUserRoom> userRooms = userRoomRepository.findByUserRoomRelationIdUserUserId(userId);

    if(userRooms.size() == 0) throw new UserRoomNotFoundException();

    return userRooms;
  }

    /**
   * get by room id
   */
  @Override
  public List<TUserRoom> getByRoomId(Integer roomId) throws UserRoomNotFoundException {


    List<TUserRoom> userRooms = userRoomRepository.findByUserRoomRelationIdRoomRoomId(roomId);

    if(userRooms.size() == 0) throw new UserRoomNotFoundException();

    return userRooms;
  }

  /**
   * get By userId and roomId
   * @throws UserRoomNotFoundException
   * @throws UserNotFoundException
   */
  @Override
  public Optional<TUserRoom> getByUserIdRoomId(Integer userId ,Integer roomId)
    throws UserRoomNotFoundException,
           UserNotFoundException ,
           RoomNotFoundException {

    //create compositekey
    UserRoomRelationId userRoomRelationId = new UserRoomRelationId();

    TUser user = userService.getById(userId).get();
    TRoom room = roomService.getById(roomId).get();

    //set composite key value
    userRoomRelationId.setUser(user);
    userRoomRelationId.setRoom(room);

    //Get composite key of userId and roomId
    Optional<TUserRoom> userRoom = userRoomRepository.findByUserRoomRelationId(userRoomRelationId);


    if(userRoom.isEmpty()) throw new UserRoomNotFoundException();

    return userRoom;
  }

  /**
   * save
   * @throws UserRoomNotSaveException
   */
  @Override
  public TUserRoom save(TUserRoom userRoom) throws UserRoomNotSaveException {
   return userRoomRepository.save(userRoom);
  }

  /**
   * update
   */
  @Override
  public TUserRoom update(TUserRoom userRoom) {
    return userRoomRepository.save(userRoom);
  }

  /**
   *  delete
   */
  @Override
  public TUserRoom delete(TUserRoom userRoom) {
     userRoomRepository.delete(userRoom);
     return userRoom;
  };


}
