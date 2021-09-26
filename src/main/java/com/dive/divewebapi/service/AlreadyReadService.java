package com.dive.divewebapi.service;

import java.util.List;

import com.dive.divewebapi.entity.TAlreadyRead;
import com.dive.divewebapi.exception.AlreadyReadNotFoundException;
import com.dive.divewebapi.exception.AlreadyReadNotSaveException;

import org.springframework.stereotype.Service;

@Service
public interface AlreadyReadService {

  public List<TAlreadyRead> getAll() throws AlreadyReadNotFoundException;

  public List<TAlreadyRead> getByUserId(Integer userId) throws AlreadyReadNotFoundException;

  public List<TAlreadyRead> getByMessageId(Integer messageId) throws AlreadyReadNotFoundException;

  public TAlreadyRead save(TAlreadyRead favorite) throws AlreadyReadNotSaveException;

  public TAlreadyRead delete(TAlreadyRead favorite);

}
