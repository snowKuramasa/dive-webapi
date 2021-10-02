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

import com.dive.divewebapi.entity.id.UserMessageFavoriteId;

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
@Table(name="t_favorite")
@Data
@NoArgsConstructor
@AllArgsConstructor
// Defined to avoid duplication
@EqualsAndHashCode

@AssociationOverrides({
  @AssociationOverride(name="userMessageFavoriteId.user", joinColumns=@JoinColumn(name="user_id")),
  @AssociationOverride(name="userMessageFavoriteId.message", joinColumns=@JoinColumn(name="message_id"))
})
// endregion common JPA annotations

public class TFavorite {

  private UserMessageFavoriteId UserMessageFavoriteId = new UserMessageFavoriteId();

  // region user_message_favorite_id column---
  /**
  * Favorite table composite primary key
  */
  //リレーションのために定義
  //外部のテーブルとキーを指定
  @Id
    public UserMessageFavoriteId getUserMessageFavoriteId() {
        return UserMessageFavoriteId;
    }
  // endregion user_message_favorite_id column---

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

  public void setUserMessageFavoriteId(UserMessageFavoriteId userMessageFavoriteId) {
    this.UserMessageFavoriteId = userMessageFavoriteId;
  }

//*Not use lombok getter/setter*
@Transient
  public TUser getUser() {
    return getUserMessageFavoriteId().getUser();
  }

  public void setUser(TUser user) {
    getUserMessageFavoriteId().setUser(user);
  }

@Transient
  public TMessage getMessage() {
    return getUserMessageFavoriteId().getMessage();
  }

  public void setMessage(TMessage message) {
    getUserMessageFavoriteId().setMessage(message);
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