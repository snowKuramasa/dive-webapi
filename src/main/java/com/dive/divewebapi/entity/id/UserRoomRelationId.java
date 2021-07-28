package com.dive.divewebapi.entity.id;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.dive.divewebapi.entity.TRoom;
import com.dive.divewebapi.entity.TUser;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Embeddable
@EqualsAndHashCode(exclude= {"user", "room"})
public class UserRoomRelationId implements Serializable  {


  private TUser user;
  private TRoom room;

  @ManyToOne(cascade = CascadeType.ALL)
  public TUser getUser(){
    return user;
  }

  public void setUser(TUser user){
    this.user = user;
  }


  @ManyToOne(cascade = CascadeType.ALL)
  public TRoom getRoom(){
    return room;
  }

  public void setRoom(TRoom room){
    this.room = room;
  }


}