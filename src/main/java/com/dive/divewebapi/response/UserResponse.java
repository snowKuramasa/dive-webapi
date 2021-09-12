package com.dive.divewebapi.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

  private Integer userId;

  private String userMail;

  private String userPassword;

  private String userName;

  private String userProfile;

  //TODO:固定値1=admin,2=userを用意してリクエスト時にセットするように修正する
  private Integer role;

  //user last login time
  private String lastLoginTime;

  //user modify time
  private String modifyTime;

  //user create time
  private String createTime;

  //#region getter/setter

  /**user lastLoginTime getter*/
  public String getLastLoginTime() { return this.lastLoginTime; }
  /**user lastLoginTime setter*/
  public void setLastLogin(Date lastLoginTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String strDate = sdf.format(lastLoginTime);
    this.lastLoginTime = strDate;
  }

  /**user createTime getter*/
  public String getCreateTime() { return this.createTime; }
  /**user createTime setter*/
  public void setCreateTime(Date createTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String strDate = sdf.format(createTime);
    this.createTime = strDate;
  }

  /**user modifyTime getter*/
  public String getModifyTime() { return this.modifyTime; }
  /**user modifyTime setter*/
  public void setModifyTime(Date modifyTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String strDate = sdf.format(modifyTime);
    this.modifyTime = strDate;
  }

  //#endregion getter/setter

  public UserResponse(TUser userEntity) {
    super();
    setUserId      (userEntity.getUserId());
    setUserMail    (userEntity.getUserMail());
    setUserPassword(userEntity.getUserPassword());
    setUserName    (userEntity.getUserName());
    setUserProfile (userEntity.getUserProfile());
    setRole (userEntity.getRole());
    setModifyTime(userEntity.getModifyTime());
    setCreateTime(userEntity.getCreateTime());
  }

}


