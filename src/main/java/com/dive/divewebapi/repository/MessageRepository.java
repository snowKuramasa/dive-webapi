package com.dive.divewebapi.repository;

import java.util.List;

import com.dive.divewebapi.entity.TMessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<TMessage, Integer> {

  //JpaRepositoryには基本的なCRUDメソッドが定義されているため、
  //このインターフェースを作成するだけで以下のメソッドが起動時に自動的に作成される
  //findOne
  //findAll
  //save
  //delete
  //NOTE:JpaRepositoryで定義されていないような検索や処理はJPQLでクエリを定義して対応する

  //#region get joincolumn

  //sender message
  List<TMessage> findBySenderUserUserId(Integer senderUserId);

  //receiver message
  List<TMessage> findByReceiverUserUserId(Integer receiverUserId);

  //#endregion get joincolumn
}
