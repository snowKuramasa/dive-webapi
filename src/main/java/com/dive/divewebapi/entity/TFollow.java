package com.dive.divewebapi.entity;

import java.sql.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dive.divewebapi.entity.id.UserFollowId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




/**
 * 中間テーブルであり、ユーザー、メッセージテーブルとリレーションする。
 * 複合主キーであり、組み合わせが一意であること。
*/

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
  @AssociationOverride(name="user_message_favorite_id.user", joinColumns=@JoinColumn(name="id")),
  @AssociationOverride(name="user_message_favorite_id.message", joinColumns=@JoinColumn(name="id"))
})
// endregion common JPA annotations

public class TFollow {

  // region user_follow_id column
  /**
  * Favorite table composite primary key
  */
  //リレーションのために定義
  //外部のテーブルとキーを指定
  @Id
  private UserFollowId user_follow_id;

  // endregion user_follow_id column

  // region create_time column
  /**
   * Created date.
   */
  @Column(nullable = false)
  private Date create_time;

  // endregion create_time column

  // region modify_time column
  /**
   * Modify date.
   */
  @Column(nullable = false)
  private Date modify_time;

  // endregion modify_time column
}