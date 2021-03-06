package com.dive.divewebapi.response;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import com.dive.divewebapi.entity.TAlreadyRead;
import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyReadResponse {

  private Integer userId;

  private Integer messageId;


  //alreadyRead modify time
  private String modifyTime;

  //alreadyRead create time
  private String createTime;

  //#region getter/setter

  /**alreadyRead createTime getter*/
  public String getCreateTime() { return this.createTime; }
  /**alreadyRead createTime setter*/
  public void setCreateTime(LocalDateTime createTime) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    this.createTime = createTime.format(dateTimeFormatter);
  }

  /**alreadyRead modifyTime getter*/
  public String getModifyTime() { return this.modifyTime; }
  /**alreadyRead modifyTime setter*/
  public void setModifyTime(LocalDateTime modifyTime) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    this.modifyTime = modifyTime.format(dateTimeFormatter);
  }

  //#endregion getter/setter

  public AlreadyReadResponse(TAlreadyRead alreadyReadEntity) {
    super();
    setUserId    (alreadyReadEntity.getUser().getUserId());
    setMessageId (alreadyReadEntity.getMessage().getMessageId());
    setModifyTime(alreadyReadEntity.getModifyTime());
    setCreateTime(alreadyReadEntity.getCreateTime());
  }

}


