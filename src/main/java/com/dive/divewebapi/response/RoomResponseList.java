package com.dive.divewebapi.response;

import java.util.ArrayList;
import java.util.List;

import com.dive.divewebapi.entity.TImage;
import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TRoom;
import com.dive.divewebapi.entity.TUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomResponseList {


  private List<RoomResponse> roomResponseList;

  //#region getter setter

  //*Use Lombok @Getter,@Setter
  // public List<RoomResponse> getRoomResponseList(){return this.roomResponseList;}

  // public void setRoomResponseList(List<RoomResponse> roomResponseList){
  //   this.roomResponseList = roomResponseList;
  // }

  //#endregion getter setter

  //#region cunstructor

  public RoomResponseList(List<TRoom> roomEntityList){

    List<RoomResponse> roomList = new ArrayList<RoomResponse>();

    roomEntityList.forEach(entity->{
      TUser roomCreater = entity.getRoomCreater();
      //TODO:リクエスト時の画像アップロードの処理が改善されるまではコメントアウトしておく
      // TImage thumbnail  = entity.getThumbnail();
      roomList.add(new RoomResponse(
        roomCreater,
        // thumbnail,
        entity));
    });

    setRoomResponseList(roomList);
  }

  //#endregion cunstructor
}
