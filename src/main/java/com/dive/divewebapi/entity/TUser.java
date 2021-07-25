package com.dive.divewebapi.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name="t_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TUser {
  // region id column
  /**
   * User ID.
   * @PrimaryKey
   */
  @Id
  @GeneratedValue
  @Column(
    name = "user_id",
    nullable = false
  )
  private Integer userId;

  // endregion id column

  // region mail column
  /**
   * User user_mail.
   */
  @Column(
    name = "user_mail",
    nullable = false
  )
  private String userMail;

  // endregion user_mail column


  // region user_password column
  /**
   * User password.
   */
  @Column(
    name = "user_password",
    nullable = false
  )
  private String userPassword;

  // endregion user_password column


  // region user_name column
  /**
   * User name.
   * The name used in the app.
   */
  @Column(
    name = "user_name",
    nullable = false
  )
  private String userName;

  // endregion user_name column


  // region user_profile column
  /**
   * User profile.
   * User profile statement.
   */
  @Column(
    name = "user_profile",
    nullable = true
  )
  private String userProfile;

  // endregion user_profile column


  // region deleted column
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

  // endregion deleted column


  // region role column
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

  // endregion role column


  // region create_time column
  /**
   * Created date.
   * User creation date.
   */
  @Column(
    name = "create_time",
    nullable = false
  )
  private Date createTime;

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
  private Date lastLoginTime;

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
  private Date modifyTime;

  // endregion modify_time column


  // region sender_id column
  /**
   * Sender's ID.
   * Message sended user ID.
   * @ForeigunKey
   */
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderUser")
  private List<TMessage> sendMessageList;

  // endregion sender_id column


  // region receiver_id column
  /**
   * Receiver's ID.
   * Message received user ID.
   * @ForeigunKey
   */
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiverUser")
  private List<TMessage> receiverMessageList;

  // endregion receiver_id column


  @OneToMany(mappedBy = "userMessageFavoriteId.user", cascade = CascadeType.ALL)
    private Set<TFavorite> favorites;

}
