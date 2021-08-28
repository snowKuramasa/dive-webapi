package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.exception.MessageNotFoundException;
import com.dive.divewebapi.exception.MessageNotSaveException;
import com.dive.divewebapi.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{
@Autowired
MessageRepository messageRepository;

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

}
