package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TFavorite;
import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.FavoriteNotFoundException;
import com.dive.divewebapi.exception.FavoriteNotSaveException;
import com.dive.divewebapi.exception.MessageNotFoundException;
import com.dive.divewebapi.exception.UserNotFoundException;

import org.springframework.stereotype.Service;

@Service
public interface FavoriteService {

  public List<TFavorite> getAll() throws FavoriteNotFoundException;

  public List<TFavorite> getByUserId(Integer userId) throws FavoriteNotFoundException;

  public List<TFavorite> getByMessageId(Integer messageId) throws FavoriteNotFoundException;

  public Optional<TFavorite> getByUserIdMessageId(Integer userId, Integer messageId) 
    throws FavoriteNotFoundException , UserNotFoundException , MessageNotFoundException;

  public TFavorite save(TFavorite favorite) throws FavoriteNotSaveException;

  public TFavorite delete(TFavorite favorite);

}
