package com.dive.divewebapi.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.dive.divewebapi.entity.TImage;
import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TRoom;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomResponse {

  private Integer roomId;

    //TODO:リクエスト時の画像アップロードの処理が改善されるまではコメントアウトしておく

  // private Integer thumbnailId;

  private Integer roomCreaterId;

  private String roomCreaterName;

  private String roomName;

  private String roomDescription;

  private String createTime;

  private String modifyTime;

  //#region getter/setter
  //*Use Lombok @Getter,@Setter

  // /**roomId getter */
  // public Integer getRoomId() {return this.roomId;}
  // /**messageId setter */
  // public void setRoomId(Integer roomId) {this.roomId = roomId;}

  // /**thumbnailId getter */
  // public Integer getThumbnailId() {return this.thumbnailId;}
  // /**thumbnailId setter */
  // public void setThumbnailId(Integer thumbnailId) {this.thumbnailId = thumbnailId;}

  // /**roomCreaterId getter */
  // public Integer getRoomCreaterId() {return this.roomCreaterId;}
  // /**roomCreaterId setter */
  // public void setRoomCreaterId(Integer roomCreaterId) {this.roomCreaterId = roomCreaterId;}

  // /**roomName getter */
  // public String getRoomName() {return this.roomName;}
  // /**roomName setter */
  // public void setRoomName(String roomName) {this.roomName = roomName;}

  // /**roomDescription getter */
  // public String getRoomDescription() {return this.roomDescription;}
  // /**roomName setter */
  // public void setRoomDescription(String roomDescription) {this.roomDescription = roomDescription;}

  /**room createTime getter*/
  public String getCreateTime() { return this.createTime; }
  /**room createTime setter*/
  public void setCreateTime(Date createTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String strDate = sdf.format(createTime);
    this.createTime = strDate;
  }

  /**room modifyTime getter*/
  public String getModifyTime() { return this.modifyTime; }
  /**room modifyTime setter*/
  public void setModifyTime(Date modifyTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String strDate = sdf.format(modifyTime);
    this.modifyTime = strDate;
  }

  //#region cunstructor
  public RoomResponse(
    TUser roomCreater,
    // TImage thumbnail,
    TRoom roomEntity
  ) {
    setRoomCreaterId  (roomCreater.getUserId());
    setRoomCreaterName(roomCreater.getUserName());

    // setThumbnailId  (thumbnail.getImageId());

    setRoomId (roomEntity.getRoomId());
    setRoomName (roomEntity.getRoomName());
    setRoomDescription (roomEntity.getRoomDescription());
    setModifyTime(roomEntity.getModifyTime());
    setCreateTime(roomEntity.getCreateTime());
  }

  //#endregion cunstructor

}



