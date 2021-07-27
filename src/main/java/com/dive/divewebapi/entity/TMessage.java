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
  private Integer messageId;

  // endregion id column


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


  // region create_time column
  /**
   * Created date.
   * Message creation date.
   */
  @Column(
    name = "create_time",
    nullable = false
  )
  private Date createTime;

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
  private Date modifyTime;

  // endregion modify_time column

  /**
   * user table foreigun key name-> name="sender_id"
   */
  @ManyToOne
  @JoinColumn(name="sender_id", nullable = false)
  private TUser senderUser;

  /**
   * user table foreigun key name-> name="receiver_id"
   */
  @ManyToOne
  @JoinColumn(name="receiver_id", nullable = true)
  private TUser receiverUser;


  @OneToMany(mappedBy = "userMessageFavoriteId.message", cascade = CascadeType.ALL)
    private Set<TFavorite> favorites;

  @OneToMany(mappedBy = "userMessageAlreadyReadId.message", cascade = CascadeType.ALL)
    private Set<TAlreadyRead> alreadyReads;

}
