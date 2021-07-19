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
public class UserMessageAlreadyReadId implements Serializable  {

  @ManyToOne(cascade = CascadeType.ALL)
  private TUser user;
  @ManyToOne(cascade = CascadeType.ALL)
  private TMessage message;
}