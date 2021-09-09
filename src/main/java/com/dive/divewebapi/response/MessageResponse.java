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
public class MessageResponse {

  private Integer messageId;

  private String message;

  private Integer senderUserId;

  private String senderUserName;

  private Integer receiverUserId;

  private String receiverUserName;

  //message modify time
  private String modifyTime;

  //message create time
  private String createTime;


  //#region getter/setter

  /**messageId getter */
  public Integer getMessageId() {return this.messageId;}
  /**messageId setter */
  public void setMessageId(Integer messageId) {this.messageId = messageId;}

  /**message getter */
  public String getMessage() {return this.message;}
  /**message setter */
  public void setMessage(String message) {this.message = message;}

  /**senderUserId getter */
  public Integer getSenderUserId() {return this.senderUserId;}
  /**senderUserId setter */
  public void setSenderUserId(Integer senderUserId) {this.senderUserId = senderUserId;}

  /**senderUserName getter */
  public String getSenderUserName() {return this.senderUserName;}
  /**senderUserName setter */
  public void setSenderUserName(String senderUserName) {this.senderUserName = senderUserName;}

  /**receiverUserId getter */
  public Integer getReceiverUserId() {return this.receiverUserId;}
  /**receiverUserId setter */
  public void setReceiverUserId(Integer receiverUserId) {this.receiverUserId = receiverUserId;}

  /**receiverUserName getter */
  public String getReceiverUserName() {return this.receiverUserName;}
  /**receiverUserName setter */
  public void setReceiverUserName(String receiverUserName) {this.receiverUserName = receiverUserName;}

  /**message createTime getter*/
  public String getCreateTime() { return this.createTime; }
  /**message createTime setter*/
  public void setCreateTime(Date createTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String strDate = sdf.format(createTime);
    this.createTime = strDate;
  }

  /**message modifyTime getter*/
  public String getModifyTime() { return this.modifyTime; }
  /**message modifyTime setter*/
  public void setModifyTime(Date modifyTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String strDate = sdf.format(modifyTime);
    this.modifyTime = strDate;
  }

  //#endregion getter/setter

  public MessageResponse(TUser senderUser, TUser receiverUser) {
    setSenderUserId  (senderUser.getUserId());
    setSenderUserName(senderUser.getUserName());

    setReceiverUserId  (receiverUser.getUserId());
    setReceiverUserName(receiverUser.getUserName());
  }

  public MessageResponse(TUser senderUser, TUser receiverUser, TMessage messageEntity) {
    setSenderUserId  (senderUser.getUserId());
    setSenderUserName(senderUser.getUserName());

    setReceiverUserId  (receiverUser.getUserId());
    setReceiverUserName(receiverUser.getUserName());

    setMessageId (messageEntity.getMessageId());
    setMessage   (messageEntity.getMessage());
    setModifyTime(messageEntity.getModifyTime());
    setCreateTime(messageEntity.getCreateTime());
  }
}


