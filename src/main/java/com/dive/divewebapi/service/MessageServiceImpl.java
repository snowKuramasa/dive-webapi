package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.MessageNotFoundException;
import com.dive.divewebapi.exception.MessageNotSaveException;
import com.dive.divewebapi.repository.MessageRepository;
import com.dive.divewebapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{
@Autowired
MessageRepository messageRepository;

UserRepository userRepository;

  /**
   * get all messages
   */
  @Override
  public List<TMessage> getAll() throws MessageNotFoundException {
    List<TMessage> messages = messageRepository.findAll();

    if(messages.size() == 0) throw new MessageNotFoundException();

    return messages;
  }

  /**
   * get by ID
   */
  @Override
  public Optional<TMessage> getById(Integer messageId) throws MessageNotFoundException {

    Optional<TMessage> message; //Optionalはnull許容型
    message = messageRepository.findById(messageId);

    if(message.isEmpty()) throw new MessageNotFoundException();

    return message;
  }

  /**
   * save
   * @throws MessageNotSaveException
   */
  @Override
  public TMessage save(TMessage message) throws MessageNotSaveException {
    //リクエストデータにmessageIdが入っているときは保存不可
    //Exception when messageId is included in request data.
    if(message.getMessageId() != null) throw new MessageNotSaveException();
    return messageRepository.save(message);
  }

  /**
   * update
   */
  @Override
  public TMessage update(TMessage message) {
    return messageRepository.save(message);
  }

  /**
   *  delete
   */
  @Override
  public TMessage delete(TMessage message) {
     messageRepository.delete(message);
     return message;
  };

  /**
   * get sender messages
   */
  @Override
  public List<TMessage> getBySenderId(Integer senderId) throws MessageNotFoundException {

    // TUser senderUser = userRepository.getById(senderId);

    List<TMessage> messages = messageRepository.findBySenderUserUserId(senderId);

    if(messages.size() == 0) throw new MessageNotFoundException();

    return messages;
  }

  /**
   * get receiver messages
   */
  @Override
  public List<TMessage> getByReceiverId(Integer receiverId) throws MessageNotFoundException {

    // TUser receiverUser = userRepository.getById(receiverId);

    List<TMessage> messages = messageRepository.findByReceiverUserUserId(receiverId);

    if(messages.size() == 0) throw new MessageNotFoundException();

    return messages;
  }

}
