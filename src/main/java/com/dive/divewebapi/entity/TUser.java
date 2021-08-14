package com.dive.divewebapi.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name="t_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TUser {
  // region id column---
  /**
   * User ID.
   * @PrimaryKey
   */
  @Id
  @GeneratedValue
  @Column(
    name = "user_id"
  )
  private Integer userId;

  // endregion id column---

  // region mail column---
  /**
   * User user_mail.
   */
  @Column(
    name = "user_mail",
    nullable = false
  )
  private String userMail;

  // endregion user_mail column---

  // region user_password column---
  /**
   * User password.
   */
  @Column(
    name = "user_password",
    nullable = false
  )
  private String userPassword;

  // endregion user_password column---

  // region user_name column---
  /**
   * User name.
   * The name used in the app.
   */
  @Column(
    name = "user_name",
    nullable = false
  )
  private String userName;

  // endregion user_name column---

  // region user_profile column---
  /**
   * User profile.
   * User profile statement.
   */
  @Column(
    name = "user_profile",
    nullable = true
  )
  private String userProfile;

  // endregion user_profile column---

  // region deleted column---
  /**
   * Deleted status.
   * User delete status.
   * (0 = false [Not deleted] / 1 = true [Deleted])
   */
  @Column(
    name = "deleted",
    nullable = false
  )
  private int deleted;

  // endregion deleted column---

  // region role column---
  /**
   * User role.
   * Roletype
   * [admin] / [user]
   */
  @Column(
    name = "role",
    nullable = false
  )
  private String role;

  // endregion role column---

  // region create_time column---
  /**
   * Created date.
   * User creation date.
   */
  @Column(
    name = "create_time"
  )
  private Date createTime;

  // endregion create_time column---


  // region last_login_time column---
  /**
   * Created date.
   * User creation date.
   */
  @Column(
    name = "last_login_time",
    // TODO:ログイン時に更新するような処理にしてnullableを外す
    nullable = false
  )
  private Date lastLoginTime;

  // endregion last_login_time column---

  // region modify_time column---
  /**
   * Modify date.
   * User update date.
   */
  @Column(
    name = "modify_time"
  )
  private Date modifyTime;

  // endregion modify_time column---

  // region sender_id column---
  /**
   * Sender's ID.
   * Message sended user ID.
   * @ForeigunKey
   */
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderUser")
  private List<TMessage> sendMessageList;

  // endregion sender_id column---

  // region receiver_id column---
  /**
   * Receiver's ID.
   * Message received user ID.
   * @ForeigunKey
   */
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiverUser")
  private List<TMessage> receiverMessageList;

  // endregion receiver_id column---

  // region room_creater_id column---
  /**
   * room creater.
   * room creater ID.
   * @ForeigunKey
   */
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomCreater")
  private List<TRoom> roomCreaterList;

  // endregion room_creater_id column---

  // region icon_id column---
  /**
   * user icon.
   * user icon ID.
   * @ForeigunKey
   */
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "icon_id", nullable = true)
  private TImage icon;

  // endregion icon_id column---

  // region relation

  @OneToMany(mappedBy = "userMessageFavoriteId.user", cascade = CascadeType.ALL)
    private Set<TFavorite> favorites;

  @OneToMany(mappedBy = "userMessageAlreadyReadId.user", cascade = CascadeType.ALL)
    private Set<TAlreadyRead> alreadyReads;

  @OneToMany(mappedBy = "userRoomRelationId.user", cascade = CascadeType.ALL)
    private Set<TUserRoomRelation> userRoomRelations;

  @OneToMany(mappedBy = "userUserFollowId.follow", cascade = CascadeType.ALL)
    private Set<TFollow> follows;

  @OneToMany(mappedBy = "userUserFollowId.follower", cascade = CascadeType.ALL)
    private Set<TFollow> followers;

  // endregion relation

  // region getter/setter

    /**user id getter*/
    public Integer getUserId() { return this.userId; }
    /**user id setter*/
    public void setUserId(Integer id) { this.userId = id; }

    /**user mail getter*/
    public String getUserMail() { return this.userMail; }
    /**user mail setter*/
    public void setUserMail(String mail) { this.userMail = mail; }

    /**user password getter*/
    public String getUserPassword() { return this.userPassword; }
    /**user password setter*/
    public void setUserPassword(String password) { this.userPassword = password; }

    /**user name getter*/
    public String getUserName() { return this.userName; }
    /**user name setter*/
    public void setUserName(String name) { this.userName = name; }

    /**user profile getter*/
    public String getUserProfile() { return this.userProfile; }
    /**user profile setter*/
    public void setUserProfile(String profile) { this.userProfile = profile; }

    /**user deleted getter*/
    public int getDeleted() { return this.deleted; }
    /**user deleted setter*/
    public void setDeleted(int deleteFrag) { this.deleted = deleteFrag; }

    /**user role getter*/
    public String getRole() { return this.role; }
    /**user role setter*/
    public void setRole(String role) { this.role = role; }

    /**user createTime getter*/
    public Date getCreateTime() { return this.createTime; }
    /**user createTime setter*/
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    /**user lastLoginTime getter*/
    public Date getLastLoginTime() { return this.lastLoginTime; }
    /**user lastLoginTime setter*/
    public void setLastLoginTime(Date lastLoginTime) { this.lastLoginTime = lastLoginTime; }

    /**user modifyTime getter*/
    public Date getModifyTime() { return this.modifyTime; }
    /**user modifyTime setter*/
    public void setModifyTime(Date modifyTime) { this.modifyTime = modifyTime; }

  // endregion getter/setter

  // region before save method
  @PrePersist
    public void onPrePersist() {
      setCreateTime(new Date());
      setModifyTime(new Date());
    }
  // endregion before save method

  // region before update method
  @PreUpdate
    public void onPreUpdate() {
      setModifyTime(new Date());
    }
  // endregion before update method

}
