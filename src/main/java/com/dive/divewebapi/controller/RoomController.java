package com.dive.divewebapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TImage;
import com.dive.divewebapi.entity.TRoom;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.RoomNotSaveException;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.requestBody.RoomRequest;
import com.dive.divewebapi.response.RoomResponse;
import com.dive.divewebapi.response.RoomResponseList;
import com.dive.divewebapi.exception.RoomNotFoundException;
import com.dive.divewebapi.service.RoomServiceImpl;
import com.dive.divewebapi.service.RoomServiceImpl;
import com.dive.divewebapi.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequestMapping("rest/rooms")
public class RoomController {
  @Autowired
 RoomServiceImpl roomService;

  @Autowired
  UserServiceImpl userService;


  @GetMapping
  ResponseEntity<RoomResponseList> getRooms() {

    try {
      List<TRoom> roomEntityList = roomService.getAll();

      RoomResponseList roomResponseList = new RoomResponseList(roomEntityList);

      return ResponseEntity.ok(roomResponseList);

    } catch (RoomNotFoundException e) {

      e.setRoom("This room not found.");
      System.err.println(e.getRoom());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  ResponseEntity<RoomResponse> getRoomsById(@PathVariable String id) {

    Integer roomId = Integer.parseInt(id);

    try {
      // get TRoom entity
      Optional<TRoom> roomOptionalEntity = roomService.getById(roomId);

      TRoom getedRoomEntity = roomOptionalEntity.get();

      //TODO:リクエスト時の画像アップロードの処理が改善されるまではコメントアウトしておく
      RoomResponse roomResponse = new RoomResponse(
                                          getedRoomEntity.getRoomCreater(),
                                          // getedRoomEntity.getThumbnail(),
                                          getedRoomEntity
                                        );

      return ResponseEntity.ok(roomResponse);

    } catch (RoomNotFoundException e) {

      e.setRoom("This room not found.");
      System.err.println(e.getRoom());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/roomcreater/{id}")
  ResponseEntity<RoomResponseList> getRoomsByRoomCreaterId(@PathVariable String id) {

    Integer roomCreaterId = Integer.parseInt(id);

    try {
      List<TRoom> roomEntityList = roomService.getByRoomCreaterId(roomCreaterId);

      RoomResponseList roomResponseList = new RoomResponseList(roomEntityList);

      return ResponseEntity.ok(roomResponseList);

    } catch (RoomNotFoundException e) {

      e.setRoom("This room not found.");
      System.err.println(e.getRoom());

      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/roomname")
  ResponseEntity<RoomResponseList> getRoomsByRoomName(
    @RequestParam(name = "name") String name
    ) {

    try {
      List<TRoom> roomEntityList = roomService.getByRoomNameContaining(name);

      RoomResponseList roomResponseList = new RoomResponseList(roomEntityList);

      return ResponseEntity.ok(roomResponseList);

    } catch (RoomNotFoundException e) {

      e.setRoom("This room not found.");
      System.err.println(e.getRoom());

      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  ResponseEntity<RoomResponse> postRoom(@RequestBody RoomRequest room) throws UserNotFoundException {

    try {
      //JPA Entity
      TRoom saveRoomEntity = new TRoom();

      //set request body
      TUser roomCreater = userService.getById(room.getRoomCreaterId()).get();
      //TODO:ImageService 追加後にコメント外す
      // TImage thumbnail  = imageService.getById(room.getThumbnailId()).get();

      saveRoomEntity.setRoomName(room.getRoomName());
      saveRoomEntity.setRoomDescription(room.getRoomDescription());
      saveRoomEntity.setRoomCreater(roomCreater);
      //TODO:ImageService 追加後にコメント外す
      // saveRoomEntity.setThumbnail(thumbnail);

      //Get saved target
      TRoom savedRoomEntity = roomService.save(saveRoomEntity);

      //Create respose
      //TODO:リクエスト時の画像アップロードの処理が改善されるまではコメントアウトしておく
      RoomResponse roomResponse = new RoomResponse(
                                          savedRoomEntity.getRoomCreater(),
                                          // savedRoomEntity.getThumbnail(),
                                          savedRoomEntity
                                        );

      //Create path for saved rooms.
      //Expected "api/rooms/{roomI}"
      URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri() //->Expected api/rooms
                .path("/{id}")                                      //->Expected api/rooms/{roomId}
                .buildAndExpand(roomResponse.getRoomId())     //->Expected insert roomId
                .toUri();

      return ResponseEntity.created(uri).body(roomResponse);

    } catch (RoomNotSaveException e) {

      e.setRoom("This room could not saved.");
      System.err.println(e.getRoom());

      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  ResponseEntity<RoomResponse> putRoom(
    @PathVariable String id,
    @RequestBody RoomRequest room
  ) throws RoomNotSaveException {

    Integer roomId = Integer.parseInt(id);

    try {

      //get TRoom entity
      TRoom updateRoomEntity = roomService.getById(roomId).get();

      //set request body
      updateRoomEntity.setRoomName(room.getRoomName());
      updateRoomEntity.setRoomDescription(room.getRoomDescription());

      //save entity
      TRoom updatedRoomEntity = roomService.update(updateRoomEntity);


      //TODO:リクエスト時の画像アップロードの処理が改善されるまではコメントアウトしておく
      RoomResponse roomResponse = new RoomResponse(
                                    updatedRoomEntity.getRoomCreater(),
                                    // updatedRoomEntity.getThumbnail(),
                                    updatedRoomEntity
                                  );

      return ResponseEntity.ok(roomResponse);

    } catch (RoomNotFoundException e) {

      e.setRoom("This room not found.");
      System.err.println(e.getRoom());

      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  ResponseEntity<RoomResponse> deleteRoom(@PathVariable String id) {

    Integer roomId = Integer.parseInt(id);

    try {
      Optional<TRoom> roomEntity = roomService.getById(roomId);

      TRoom deleteRoomEntity = roomEntity.get();

      TRoom deletedRoomEntity = roomService.delete(deleteRoomEntity);

      //TODO:リクエスト時の画像アップロードの処理が改善されるまではコメントアウトしておく
      RoomResponse roomResponse = new RoomResponse(
                                    deletedRoomEntity.getRoomCreater(),
                                    // deletedRoomEntity.getThumbnail(),
                                    deletedRoomEntity
                                  );

      return ResponseEntity.ok(roomResponse);

    } catch (RoomNotFoundException e) {

      e.setRoom("This room not found.");
      System.err.println(e.getRoom());

      return ResponseEntity.notFound().build();
    }
  }
}
