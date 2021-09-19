package com.dive.divewebapi.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.dive.divewebapi.entity.TFavorite;
import com.dive.divewebapi.entity.TMessage;
import com.dive.divewebapi.entity.TUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<TFavorite,Integer> {

  //JpaRepositoryには基本的なCRUDメソッドが定義されているため、
  //このインターフェースを作成するだけで以下のメソッドが起動時に自動的に作成される
  //findOne
  //findAll
  //save
  //delete

  //get by userId favorite
  List<TFavorite> findByUserMessageFavoriteIdUserUserId(Integer userId);

  //get by messageId favorite
  List<TFavorite> findByUserMessageFavoriteIdMessageMessageId(Integer messageId);

  //get by userId and messageId
  // Optional<TFavorite> findByUserMessageFavoriteIdUserUserIdAndMessageMessageId(Integer userId, Integer messageId);
}
