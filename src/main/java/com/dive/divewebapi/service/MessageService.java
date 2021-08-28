package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.exception.MessageNotFoundException;
import com.dive.divewebapi.exception.MessageNotSaveException;

import org.springframework.stereotype.Service;

@Service
public interface MessageService {

  public List<TMessage> getAll() throws MessageNotFoundException;

  public Optional<TMessage>  getById(Integer messageId) throws MessageNotFoundException;

  public TMessage save(TMessage message) throws MessageNotSaveException;

  public TMessage update(TMessage message);


}
