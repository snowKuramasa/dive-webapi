package com.dive.divewebapi.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.dive.divewebapi.entity.TFavorite;
import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteResponse {

  private Integer userId;

  private Integer messageId;


  //favorite modify time
  private String modifyTime;

  //favorite create time
  private String createTime;

  //#region getter/setter

  /**favorite createTime getter*/
  public String getCreateTime() { return this.createTime; }
  /**favorite createTime setter*/
  public void setCreateTime(Date createTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String strDate = sdf.format(createTime);
    this.createTime = strDate;
  }

  /**favorite modifyTime getter*/
  public String getModifyTime() { return this.modifyTime; }
  /**favorite modifyTime setter*/
  public void setModifyTime(Date modifyTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String strDate = sdf.format(modifyTime);
    this.modifyTime = strDate;
  }

  //#endregion getter/setter

  public FavoriteResponse(TFavorite favoriteEntity) {
    super();
    setUserId    (favoriteEntity.getUser().getUserId());
    setMessageId    (favoriteEntity.getMessage().getMessageId());
    setModifyTime(favoriteEntity.getModifyTime());
    setCreateTime(favoriteEntity.getCreateTime());
  }

}


