package com.dive.divewebapi.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
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
    name = "id",
    nullable = false
  )
  private Integer id;

  // endregion id column

  // region mail column
  /**
   * User user_mail.
   */
  @Column(
    name = "user_mail",
    nullable = false
  )
  private String user_mail;

  // endregion user_mail column


  // region user_password column
  /**
   * User password.
   */
  @Column(
    name = "user_password",
    nullable = false
  )
  private String user_password;

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
  private String user_name;

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
  private String user_profile;

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


  // region icon_id column
  /**
   * User icon id.
   * User icon image id.
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
        name ="icon_id",
        //参照先カラム名
        referencedColumnName ="id",
        nullable = true
      )
    }
  )
  private Integer icon_id;

  // endregion icon_id column


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
