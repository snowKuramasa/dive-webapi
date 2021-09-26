package com.dive.divewebapi.repository;

import java.util.List;

import com.dive.divewebapi.entity.TAlreadyRead;
import com.dive.divewebapi.entity.id.UserMessageAlreadyReadId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlreadyReadRepository extends JpaRepository<TAlreadyRead,UserMessageAlreadyReadId> {

  //JpaRepositoryには基本的なCRUDメソッドが定義されているため、
  //このインターフェースを作成するだけで以下のメソッドが起動時に自動的に作成される
  //findOne
  //findAll
  //save
  //delete

  //get by userId alresdyRead
  List<TAlreadyRead> findByUserMessageAlreadyReadIdUserUserId(Integer userId);

  //get by messageId alresdyRead
  List<TAlreadyRead> findByUserMessageAlreadyReadIdMessageMessageId(Integer messageId);

}
