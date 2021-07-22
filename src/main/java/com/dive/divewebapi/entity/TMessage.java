package com.dive.divewebapi.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    name = "message_id",
    nullable = false
  )
  private Integer message_id;

  // endregion id column


  // // region sender_id column
  // /**
  //  * Sender's ID.
  //  * Message sended user ID.
  //  * @ForeigunKey
  //  */
  // //リレーションのために定義
  // @OneToMany
  // //外部のテーブルとキーを指定

  // @JoinColumn (
  //   //カラム名
  //   name ="sender_id",
  //   //参照先カラム名
  //   referencedColumnName ="user_id",
  //   nullable = false
  // )
  // private Integer sender_id;

  // // endregion sender_id column


  // // region receiver_id column
  // /**
  //  * Receiver's ID.
  //  * Message received user ID.
  //  * @ForeigunKey
  //  */
  // // リレーションのために定義
  // @OneToMany
  // //外部のテーブルとキーを指定
  // @JoinColumn (
  //   //カラム名
  //   name ="receiver_id",
  //   //参照先カラム名
  //   referencedColumnName ="user_id",
  //   nullable = false
  // )
  // private Integer receiver_id;

  // // endregion receiver_id column


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


  // // region room_id column
  // /**
  //  * Chat room ID
  //  * ID of the room to which the Message belongs.
  //  * @ForeigunKey
  //  */
  // //リレーションのために定義
  // @OneToMany
  // //外部のテーブルとキーを指定
  // @JoinColumn (
  //   //カラム名
  //   name ="room_id",
  //   //参照先カラム名
  //   referencedColumnName ="room_id",
  //   nullable = false
  // )
  // private Integer room_id;

  // // endregion room_id column


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

  @ManyToOne
  @JoinColumn(name="sender_id", nullable = false)
  private TUser sender_user;

  @ManyToOne
  @JoinColumn(name="receiver_id", nullable = true)
  private TUser receiver_user;

  @ManyToOne
  @JoinColumn(name="room_id" , nullable = false)
  private TRoom room;

  @OneToMany(mappedBy = "user_message_favorite_id", cascade = CascadeType.ALL)
  private Set<TFavorite> favorities;

  @OneToMany(mappedBy = "user_message_already_read_id", cascade = CascadeType.ALL)
  private Set<TAlreadyRead> already_read;
}
