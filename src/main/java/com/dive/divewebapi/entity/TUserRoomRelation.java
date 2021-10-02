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

import com.dive.divewebapi.entity.id.UserRoomRelationId;

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
@Table(name="t_user_room_relation")
@Data
@NoArgsConstructor
@AllArgsConstructor
// Defined to avoid duplication
@EqualsAndHashCode

@AssociationOverrides({
  @AssociationOverride(name="userRoomRelationId.user", joinColumns=@JoinColumn(name="user_id")),
  @AssociationOverride(name="userRoomRelationId.room", joinColumns=@JoinColumn(name="room_id"))
})
// endregion common JPA annotations

public class TUserRoomRelation {

  private UserRoomRelationId userRoomRelationId = new UserRoomRelationId();

  // region user_room_relation_id column---
  /**
  * Favorite table composite primary key
  */
  //リレーションのために定義
  //外部のテーブルとキーを指定
  @Id
  public UserRoomRelationId getUserRoomRelationId() {
      return userRoomRelationId;
  }
  // endregion user_room_relation_id column---

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