package com.dive.divewebapi.response;

import java.util.ArrayList;
import java.util.List;

import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;

public class MessageResponseList {


  private List<MessageResponse> messageResponseList;

  public List<MessageResponse> getMessageResponseList(){return this.messageResponseList;}

  public void setMessageResponseList(List<MessageResponse> messageResponseList){
    this.messageResponseList = messageResponseList;
  }

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
