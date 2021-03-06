package com.dive.divewebapi.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.dive.divewebapi.entity.id.UserMessageAlreadyReadId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



// region common JPA annotations
@Getter
@Setter
@Entity
@Table(name="t_already_read")
@Data
@NoArgsConstructor
@AllArgsConstructor
// Defined to avoid duplication
@EqualsAndHashCode

@AssociationOverrides({
  @AssociationOverride(name="userMessageAlreadyReadId.user", joinColumns=@JoinColumn(name="user_id")),
  @AssociationOverride(name="userMessageAlreadyReadId.message", joinColumns=@JoinColumn(name="message_id"))
})
// endregion common JPA annotations

public class TAlreadyRead {

  private UserMessageAlreadyReadId userMessageAlreadyReadId = new UserMessageAlreadyReadId();

  // region user_message_already_read_id column---
  /**
  * Favorite table composite primary key
  */
  //リレーションのために定義
  //外部のテーブルとキーを指定
  @Id
    public UserMessageAlreadyReadId getUserMessageAlreadyReadId() {
      return userMessageAlreadyReadId;
    }
  // endregion user_message_already_read_id column---

  // region create_time column---
  /**
   * Created date.
   */
  @Column(nullable = false)
    private LocalDateTime createTime;

  // endregion create_time column---

  // region modify_time column---
  /**
   * Modify date.
   */
  @Column(nullable = false)
    private LocalDateTime modifyTime;

  // endregion modify_time column---


    public void setUserMessageAlreadyReadId(UserMessageAlreadyReadId userMessageAlreadyReadId) {
      this.userMessageAlreadyReadId = userMessageAlreadyReadId;
    }

@Transient
  public TUser getUser() {
      return getUserMessageAlreadyReadId().getUser();
  }

  public void setUser(TUser user) {
    getUserMessageAlreadyReadId().setUser(user);
  }

@Transient
  public TMessage getMessage() {
      return getUserMessageAlreadyReadId().getMessage();
  }

  public void setMessage(TMessage message) {
    getUserMessageAlreadyReadId().setMessage(message);
  }

  // region before save method
  @PrePersist
    public void onPrePersist() {
      setCreateTime(LocalDateTime.now());
      setModifyTime(LocalDateTime.now());
  }
  // endregion before save method

  // region before update method
  @PreUpdate
    public void onPreUpdate() {
      setModifyTime(LocalDateTime.now());
    }
  // endregion before update method
}