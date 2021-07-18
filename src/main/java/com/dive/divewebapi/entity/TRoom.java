package com.dive.divewebapi.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA annotations
*/
@Getter
@Setter
@Entity
@Table(name="t_room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TRoom {

  // region id column
  /**
   * Image ID.
   * @PrimaryKey
   */
  @Id
  @GeneratedValue
  @Column(
    name = "id",
    nullable = false
  )
  private Integer id;

  // endregion id column

  // region thumbnail_id column
  /**
   * thumbnail id.
   * @ForeigunKey
   */
  @OneToOne
  //外部のテーブルとキーを指定
  @JoinTable(
    //参照先テーブル名
    name="t_image",
    joinColumns = {
      @JoinColumn (
        //カラム名
        name ="thumbnail_id",
        //参照先カラム名
        referencedColumnName ="id",
        nullable = false
      )
    }
  )
  private Integer thumbnail_id;

  // endregion thumbnail_id column


  // region room_creater_id column
  /**
   * Room creator id.
   * @ForeigunKey
   */
  @ManyToOne
  //外部のテーブルとキーを指定
  @JoinTable(
    //参照先テーブル名
    name="t_user",
    joinColumns = {
      @JoinColumn (
        //カラム名
        name ="room_creater_id",
        //参照先カラム名
        referencedColumnName ="id",
        nullable = false
      )
    }
  )
  private Integer room_creater_id;

  // endregion room_creater_id column


  // region room_name column
  /**
   * Image name.
   */
  @Column(
    name = "room_name",
    nullable = false
  )
  private String room_name;

  // endregion room_name column


  // region description column
  /**
   * Room description.
   */
  @Column(
    name = "room_description",
    nullable = true
  )
  private String room_description;

  // endregion description column


  // region create_time column
  /**
   * Created date.
   * User creation date.
   */
  @Column(
    name = "create_time",
    nullable = false
  )
  private Date create_time;

  // endregion create_time column


  // region last_login_time column
  /**
   * Created date.
   * User creation date.
   */
  @Column(
    name = "last_login_time",
    nullable = false
  )
  private Date last_login_time;

  // endregion last_login_time column


  // region modify_time column
  /**
   * Modify date.
   * User update date.
   */
  @Column(
    name = "modify_time",
    nullable = false
  )
  private Date modify_time;

  // endregion modify_time column
}
