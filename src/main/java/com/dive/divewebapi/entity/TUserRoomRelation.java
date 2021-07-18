package com.dive.divewebapi.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
@Table(name="t_user_room_relation")
@Data
@NoArgsConstructor
@AllArgsConstructor
// Defined to avoid duplication
@EqualsAndHashCode

// endregion common JPA annotations

public class TUserRoomRelation {

  // region user_id column
  /**
  * Favorite table composite primary key
  */
  //リレーションのために定義
  @ManyToOne
  //外部のテーブルとキーを指定
  @JoinTable(
    //参照先テーブル名
    name="t_user",
    joinColumns = {
      @JoinColumn (
        //カラム名
        name ="user_id",
        //参照先カラム名
        referencedColumnName ="id",
        nullable = false
      )
    }
  )
  @Id
  private Integer user_id;

  // endregion user_id column

  // region room_id column
  /**
  * Favorite table composite primary key
  */
  @ManyToOne
  //外部のテーブルとキーを指定
  @JoinTable(
    //参照先テーブル名
    name="t_room",
    joinColumns = {
      @JoinColumn (
        //カラム名
        name ="room_id",
        //参照先カラム名
        referencedColumnName ="id",
        nullable = false
      )
    }
  )
  @Id
  private Integer room_id;

  // endregion room_id column

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