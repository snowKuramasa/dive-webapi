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
@Table(name="t_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TImage {

  // region id column---
  /**
   * Image ID.
   * @PrimaryKey
   */
  @Id
  @GeneratedValue
  @Column(
    name = "image_id",
    nullable = false
  )
  private Integer image_id;

  // endregion id column---

  // region image_url column---
  /**
   * Image url.
   */
  @Column(
    name = "image_url",
    nullable = false
  )
  private String image_url;

  // endregion image_url column---

  // region image_name column---
  /**
   * Image name.
   */
  @Column(
    name = "image_name",
    nullable = false
  )
  private String image_name;

  // endregion image_name column---


  // region description column---
  /**
   * Room description.
   */
  @Column(
    name = "image_description",
    nullable = true
  )
  private String image_description;

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
  private Date create_time;

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
  private Date modify_time;

  // endregion modify_time column---


  @OneToOne(mappedBy = "icon")
    private TUser user;

  @OneToOne(mappedBy = "thumbnail")
    private TRoom room;

}