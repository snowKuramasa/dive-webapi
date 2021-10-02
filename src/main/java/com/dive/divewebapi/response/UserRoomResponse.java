package com.dive.divewebapi.response;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import com.dive.divewebapi.entity.TUserRoom;
import com.dive.divewebapi.entity.TRoom;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoomResponse {

  private Integer userId;

  private Integer roomId;


  //userRoom modify time
  private String modifyTime;

  //userRoom create time
  private String createTime;

  //#region getter/setter

  /**userRoom createTime getter*/
  public String getCreateTime() { return this.createTime; }
  /**userRoom createTime setter*/
  public void setCreateTime(LocalDateTime createTime) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    this.createTime = createTime.format(dateTimeFormatter);
  }

  /**userRoom modifyTime getter*/
  public String getModifyTime() { return this.modifyTime; }
  /**userRoom modifyTime setter*/
  public void setModifyTime(LocalDateTime modifyTime) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    this.modifyTime = modifyTime.format(dateTimeFormatter);
  }

  //#endregion getter/setter

  public UserRoomResponse(TUserRoom userRoomEntity) {
    super();
    setUserId    (userRoomEntity.getUser().getUserId());
    setRoomId    (userRoomEntity.getRoom().getRoomId());
    setModifyTime(userRoomEntity.getModifyTime());
    setCreateTime(userRoomEntity.getCreateTime());
  }

}


