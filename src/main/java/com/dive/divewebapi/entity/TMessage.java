package com.dive.divewebapi.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="t_message")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TMessage {

  // region id column
  /**
   * Message ID.
   * @PrimaryKey
   */
  @Id
  @Getter
	@Setter
  @GeneratedValue
  @Column(
    name = "id",
    nullable = false
  )
  private Integer id;

  // endregion id column


  // region sender_id column
  /**
   * Sender's ID.
   * Message sended user ID.
   * @ForeigunKey
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
        name ="sender_id",
        //参照先カラム名
        referencedColumnName ="id",
        nullable = false
      )
    }
  )
  private Integer sender_id;

  // endregion sender_id column


  // region receiver_id column
  /**
   * Receiver's ID.
   * Message received user ID.
   * @ForeigunKey
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
        name ="receiver_id",
        //参照先カラム名
        referencedColumnName ="id",
        nullable = true
      )
    }
  )
  private Integer receiver_id;

  // endregion receiver_id column


  // region message column
  /**
   * Message.
   * Message content.
   */
  @Column(
    name = "message",
    nullable = true
  )
  private String message;

  // endregion message column


  // region room_id column
  /**
   * Chat room ID
   * ID of the room to which the Message belongs.
   * @ForeigunKey
   */
  //リレーションのために定義
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
  private Integer room_id;

  // endregion room_id column


  // region create_time column
  /**
   * Created date.
   * Message creation date.
   */
  @Column(
    name = "create_time",
    nullable = false
  )
  private Date create_time;

  // endregion create_time column


  // region modify_time column
  /**
   * Modify date.
   * Message update date.
   */
  @Column(
    name = "modify_time",
    nullable = false
  )
  private Date modify_time;

  // endregion modify_time column
}
