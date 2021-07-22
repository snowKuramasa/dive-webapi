package com.dive.divewebapi.entity;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    name = "room_id",
    nullable = false
  )
  private Integer room_id;

  // endregion id column

  // // region thumbnail_id column
  // /**
  //  * thumbnail id.
  //  * @ForeigunKey
  //  */
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "thumbnail_id", nullable = true)
  private TImage thumbnail;

  // // endregion thumbnail_id column


  // region room_creater_id column
  /**
   * Room creator id.
   * @ForeigunKey
   */
  @ManyToOne
  @JoinColumn(name="room_creater_id", nullable = false)
  private TUser room_creater;

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

  // region room_id column
  /**
   * Chat room ID
   * ID of the room to which the Message belongs.
   * @ForeigunKey
   */
  // //リレーションのために定義
  @OneToMany( mappedBy = "room", cascade = CascadeType.ALL)
  // @OneToMany(cascade = CascadeType.ALL)
  // @JoinColumn(name = "room_id", nullable = true)
  private List<TMessage> room_message_list;

  // endregion room_id column

  @OneToMany(mappedBy = "user_room_relation_id", cascade = CascadeType.ALL)
  private Set<TUserRoomRelation> user_room_relations;
}
