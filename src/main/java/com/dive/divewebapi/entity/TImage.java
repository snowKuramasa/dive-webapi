package com.dive.divewebapi.entity;

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
    name = "image_id"
  )
  private Integer imageId;

  // endregion id column---

  // region image_file_name column---
  /**
   * Image name.
   */
  @Column(
    name = "image_file_name"
  )
  private String imageFileName;

  // endregion image_file_name column---

  // region image_url column---
  /**
   * Image url.
   */
  @Column(
    name = "image_url",
    nullable = false
  )
  private String imageUrl;

  // endregion image_url column---

  // region image_name column---
  /**
   * Image name.
   */
  @Column(
    name = "image_name",
    nullable = false
  )
  private String imageName;

  // endregion image_name column---


  // region description column---
  /**
   * Room description.
   */
  @Column(
    name = "image_description",
    nullable = true
  )
  private String imageDescription;

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
  private Date createTime;

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
  private Date modifyTime;

  // endregion modify_time column---


  @OneToOne(mappedBy = "icon")
    private TUser user;

  @OneToOne(mappedBy = "thumbnail")
    private TRoom room;


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