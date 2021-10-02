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

import com.dive.divewebapi.entity.id.UserUserFollowId;

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
@Table(name="t_follow")
@Data
@NoArgsConstructor
@AllArgsConstructor
// Defined to avoid duplication
@EqualsAndHashCode

@AssociationOverrides({
  @AssociationOverride(name="userUserFollowId.follow", joinColumns=@JoinColumn(name="follow_id")),
  @AssociationOverride(name="userUserFollowId.follower", joinColumns=@JoinColumn(name="follower_id"))
})
// endregion common JPA annotations

public class TFollow {

  private UserUserFollowId userUserFollowId = new UserUserFollowId();

  // region user_user_follow_id column---
  /**
  * Favorite table composite primary key
  */
  //リレーションのために定義
  //外部のテーブルとキーを指定
  @Id
  public UserUserFollowId getUserUserFollowId() {
      return userUserFollowId;
  }
  // endregion user_user_follow_id column---

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


  public void setUserUserFollowId(UserUserFollowId userUserFollowId) {
      this.userUserFollowId = userUserFollowId;
  }

  @Transient
  public TUser getFollow() {
      return getUserUserFollowId().getFollow();
  }

  public void setFollow(TUser follow) {
    getUserUserFollowId().setFollow(follow);
  }

  @Transient
  public TUser getFollower() {
      return getUserUserFollowId().getFollower();
  }

  public void setFollower(TUser follower) {
    getUserUserFollowId().setFollower(follower);
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