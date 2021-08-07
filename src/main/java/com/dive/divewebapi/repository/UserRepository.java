package com.dive.divewebapi.repository;

import com.dive.divewebapi.entity.TUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TUser, Integer> {

  //JpaRepositoryには基本的なCRUDメソッドが定義されているため、
  //このインターフェースを作成するだけで以下のメソッドが起動時に自動的に作成される
  //findOne
  //findAll
  //save
  //delete
  //Jparepositoryで定義されていないような検索や処理はJPQLでクエリを定義して対応する

}
