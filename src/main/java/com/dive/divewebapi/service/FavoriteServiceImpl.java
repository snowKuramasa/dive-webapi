package com.dive.divewebapi.service;

import java.util.List;
import java.util.Optional;

import com.dive.divewebapi.entity.TFavorite;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.exception.FavoriteNotFoundException;
import com.dive.divewebapi.exception.FavoriteNotSaveException;
import com.dive.divewebapi.repository.FavoriteRepository;
import com.dive.divewebapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService{
@Autowired
FavoriteRepository favoriteRepository;

UserRepository userRepository;

  /**
   * get all favorites
   */
  @Override
  public List<TFavorite> getAll() throws FavoriteNotFoundException {
    List<TFavorite> favorites = favoriteRepository.findAll();

    if(favorites.size() == 0) throw new FavoriteNotFoundException();

    return favorites;
  }

  /**
   * save
   * @throws FavoriteNotSaveException
   */
  @Override
  public TFavorite save(TFavorite favorite) throws FavoriteNotSaveException {
   return favoriteRepository.save(favorite);
  }

  /**
   *  delete
   */
  @Override
  public TFavorite delete(TFavorite favorite) {
     favoriteRepository.delete(favorite);
     return favorite;
  };

  /**
   * get by user id
   */
  @Override
  public List<TFavorite> getByUserId(Integer userId) throws FavoriteNotFoundException {


    List<TFavorite> favorites = favoriteRepository.findByUserMessageFavoriteIdUserUserId(userId);

    if(favorites.size() == 0) throw new FavoriteNotFoundException();

    return favorites;
  }


}
