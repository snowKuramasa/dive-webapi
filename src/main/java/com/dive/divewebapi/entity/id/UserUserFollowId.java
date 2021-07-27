package com.dive.divewebapi.entity.id;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.dive.divewebapi.entity.TUser;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Embeddable
@EqualsAndHashCode(exclude= {"follow", "follower"})
public class UserUserFollowId implements Serializable  {


  private TUser follow;
  private TUser follower;

  @ManyToOne(cascade = CascadeType.ALL)
  public TUser getFollow(){
    return follow;
  }

  public void setFollow(TUser follow){
    this.follow = follow;
  }


  @ManyToOne(cascade = CascadeType.ALL)
  public TUser getFollower(){
    return follower;
  }

  public void setFollower(TUser follower){
    this.follower = follower;
  }


}