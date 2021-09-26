package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TAlreadyRead;
import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.entity.id.UserMessageAlreadyReadId;
import com.dive.divewebapi.exception.AlreadyReadNotFoundException;
import com.dive.divewebapi.exception.AlreadyReadNotSaveException;
import com.dive.divewebapi.exception.MessageNotFoundException;
import com.dive.divewebapi.exception.UserNotFoundException;
import com.dive.divewebapi.repository.AlreadyReadRepository;
import com.dive.divewebapi.repository.AlreadyReadRepository;
import com.dive.divewebapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlreadyReadServiceImpl implements AlreadyReadService{
@Autowired
AlreadyReadRepository alreadyReadRepository;

@Autowired
UserRepository userRepository;

@Autowired
MessageServiceImpl messageService;

@Autowired
UserServiceImpl userService;


  /**
   * get all alreadyReads
   */
  @Override
  public List<TAlreadyRead> getAll() throws AlreadyReadNotFoundException {
    List<TAlreadyRead> alreadyReads = alreadyReadRepository.findAll();

    if(alreadyReads.size() == 0) throw new AlreadyReadNotFoundException();

    return alreadyReads;
  }

  /**
   * get by user id
   */
  @Override
  public List<TAlreadyRead> getByUserId(Integer userId) throws AlreadyReadNotFoundException {


    List<TAlreadyRead> alreadyReads = alreadyReadRepository.findByUserMessageAlreadyReadIdUserUserId(userId);

    if(alreadyReads.size() == 0) throw new AlreadyReadNotFoundException();

    return alreadyReads;
  }

    /**
   * get by message id
   */
  @Override
  public List<TAlreadyRead> getByMessageId(Integer messageId) throws AlreadyReadNotFoundException {


    List<TAlreadyRead> alreadyReads = alreadyReadRepository.findByUserMessageAlreadyReadIdMessageMessageId(messageId);

    if(alreadyReads.size() == 0) throw new AlreadyReadNotFoundException();

    return alreadyReads;
  }


  /**
   * save
   * @throws AlreadyReadNotSaveException
   */
  @Override
  public TAlreadyRead save(TAlreadyRead alreadyRead) throws AlreadyReadNotSaveException {
   return alreadyReadRepository.save(alreadyRead);
  }

  /**
   *  delete
   */
  @Override
  public TAlreadyRead delete(TAlreadyRead alreadyRead) {
     alreadyReadRepository.delete(alreadyRead);
     return alreadyRead;
  };


}
