package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TFavorite;
import com.dive.divewebapi.exception.FavoriteNotFoundException;
import com.dive.divewebapi.exception.FavoriteNotSaveException;

import org.springframework.stereotype.Service;

@Service
public interface FavoriteService {

  public List<TFavorite> getAll() throws FavoriteNotFoundException;

  public List<TFavorite> getByUserId(Integer userId) throws FavoriteNotFoundException;

  public TFavorite save(TFavorite user) throws FavoriteNotSaveException;

  public TFavorite delete(TFavorite user);

}
