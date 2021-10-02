package com.dive.divewebapi.entity;


import java.time.LocalDateTime;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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

  // region id column---
  /**
   * Room ID.
   * @PrimaryKey
   */
  @Id
  @GeneratedValue
  @Column(
    name = "room_id"
  )
  private Integer roomId;

  // endregion id column---

  // region thumbnail_id column---
  // /**
  //  * thumbnail id.
  //  * @ForeigunKey
  //  */
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "thumbnail_id", nullable = true)
  private TImage thumbnail;

  // endregion thumbnail_id column---


  // region room_creater_id column---

  /**
   * user table foreigun key name-> name="room_creater_id"
   */
  @ManyToOne
  @JoinColumn(name="room_creater_id", nullable = false)
  private TUser roomCreater;

  // endregion room_creater_id column---

  // region room_name column---
  /**
   * Image name.
   */
  @Column(
    name = "room_name",
    nullable = false
  )
  private String roomName;

  // endregion room_name column---


  // region description column---
  /**
   * Room description.
   */
  @Column(
    name = "room_description",
    nullable = true
  )
  private String roomDescription;

  // endregion description column---


  // region create_time column---
  /**
   * Created date.
   * User creation date.
   */
  @Column(
    name = "create_time",
    nullable = false
  )
  private LocalDateTime createTime;

  // endregion create_time column---


  // region modify_time column---
  /**
   * Modify date.
   * User update date.
   */
  @Column(
    name = "modify_time",
    nullable = false
  )
  private LocalDateTime modifyTime;

  // endregion modify_time column---


  // region relation
  /**
   * Belong to message ID.
   * Send Message in the room of ID.
   * @ForeigunKey
   */
  @OneToMany(
    cascade = CascadeType.ALL,
    mappedBy = "belongToRoom"
  )
  private List<TMessage> belongToRoomList;



  @OneToMany(mappedBy = "userRoomRelationId.user", cascade = CascadeType.ALL)
  private Set<TUserRoom> userRoomRelations;

  // endregion relation

  // region getter/setter
  //*Use Lombok @Getter,@Setter

    // /**room id getter*/
    // public Integer getRoomId() { return this.roomId; }
    // /**room id setter*/
    // public void setRoomId(Integer id) { this.roomId = id; }

    // /**thumbnail getter*/
    // public TImage getThumbnail() { return this.thumbnail; }
    // /**thumbnail setter*/
    // public void setThumbnail(TImage thumbnail) { this.thumbnail = thumbnail; }

    // /**roomCreater getter*/
    // public TUser getRoomCreater() { return this.roomCreater; }
    // /**roomCreater setter*/
    // public void setRoomCreater(TUser roomCreater) { this.roomCreater = roomCreater; }

    // /**roomName getter*/
    // public String getRoomName() { return this.roomName; }
    // /**roomName setter*/
    // public void setRoomName(String roomName) { this.roomName = roomName; }

    // /**roomDescription getter*/
    // public String getRoomDescription() { return this.roomDescription; }
    // /**roomDescription setter*/
    // public void setRoomDescription(String roomDescription) { this.roomDescription = roomDescription; }

    // /**message createTime getter*/
    // public Date getCreateTime() { return this.createTime; }
    // /**message createTime setter*/
    // public void setCreateTime(Date createTime) { this.createTime = createTime; }

    // /**message modifyTime getter*/
    // public Date getModifyTime() { return this.modifyTime; }
    // /**message modifyTime setter*/
    // public void setModifyTime(Date modifyTime) { this.modifyTime = modifyTime; }

    // endregion getter/setter

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