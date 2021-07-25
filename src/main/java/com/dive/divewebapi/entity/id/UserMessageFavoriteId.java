package com.dive.divewebapi.entity.id;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Embeddable
@EqualsAndHashCode(exclude= {"user", "message"})
public class UserMessageFavoriteId implements Serializable  {


  private TUser user;
  private TMessage message;

  @ManyToOne(cascade = CascadeType.ALL)
  public TUser getUser(){
    return user;
  }

  public void setUser(TUser user){
    this.user = user;
  }


  @ManyToOne(cascade = CascadeType.ALL)
  public TMessage getMessage(){
    return message;
  }

  public void setMessage(TMessage message){
    this.message = message;
  }


}