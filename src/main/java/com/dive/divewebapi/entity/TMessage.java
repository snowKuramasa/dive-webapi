package com.dive.divewebapi.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    name = "message_id"
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
    name = "create_time"
  )
  private Date createTime;

  // endregion create_time column


  // region modify_time column
  /**
   * Modify date.
   * Message update date.
   */
  @Column(
    name = "modify_time"
  )
  private Date modifyTime;

  // endregion modify_time column

  /**
   * message table foreigun key name-> name="sender_id"
   */
  @ManyToOne
  @JoinColumn(
    name="sender_id", 
    nullable = false
  )
  private TUser senderUser;

  /**
   * message table foreigun key name-> name="receiver_id"
   */
  @ManyToOne
  @JoinColumn(
    name="receiver_id",
    nullable = true
  )
  private TUser receiverUser;


  @OneToMany(mappedBy = "userMessageFavoriteId.message", cascade = CascadeType.ALL)
    private Set<TFavorite> favorites;

  @OneToMany(mappedBy = "userMessageAlreadyReadId.message", cascade = CascadeType.ALL)
    private Set<TAlreadyRead> alreadyReads;



  // region getter/setter

    /**message id getter*/
    public Integer getMessageId() { return this.messageId; }
    /**message id setter*/
    public void setMessageId(Integer id) { this.messageId = id; }

    /**message message getter*/
    public String getMessage() { return this.message; }
    /**message message setter*/
    public void setMessage(String message) { this.message = message; }

    /**message createTime getter*/
    public Date getCreateTime() { return this.createTime; }
    /**message createTime setter*/
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    /**message modifyTime getter*/
    public Date getModifyTime() { return this.modifyTime; }
    /**message modifyTime setter*/
    public void setModifyTime(Date modifyTime) { this.modifyTime = modifyTime; }

    /**message senderUser getter*/
    public TUser getSenderUser() { return this.senderUser; }
    /**message senderUser setter*/
    public void setSenderUser(TUser senderUser) { this.senderUser = senderUser; }

    /**message receiverUser getter*/
    public TUser getReceiverUser() { return this.receiverUser; }
    /**message receiverUser setter*/
    public void setReceiverUser(TUser receiverUser) { this.receiverUser = receiverUser; }

      // endregion getter/setter


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
