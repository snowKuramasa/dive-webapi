package com.dive.divewebapi.response;

import java.util.ArrayList;
import java.util.List;

import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseList {


  private List<MessageResponse> messageResponseList;

  //#region getter setter
  // public List<MessageResponse> getMessageResponseList(){return this.messageResponseList;}

  // public void setMessageResponseList(List<MessageResponse> messageResponseList){
  //   this.messageResponseList = messageResponseList;
  // }
  //#endregion getter setter

  public MessageResponseList(List<TMessage> messageEntityList){

    List<MessageResponse> messageList = new ArrayList<MessageResponse>();

    messageEntityList.forEach(entity->{
      TUser senderUser   = entity.getSenderUser();
      TUser receiverUser = entity.getReceiverUser();
      messageList.add(new MessageResponse(senderUser, receiverUser, entity));
    });

    setMessageResponseList(messageList);
  }
}
