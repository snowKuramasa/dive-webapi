package com.dive.divewebapi.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.dive.divewebapi.entity.TRoom;
import com.dive.divewebapi.entity.TUser;
import com.dive.divewebapi.entity.TUserRoom;
import com.dive.divewebapi.entity.id.UserRoomRelationId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoomRepository extends JpaRepository<TUserRoom,UserRoomRelationId> {

  //JpaRepositoryには基本的なCRUDメソッドが定義されているため、
  //このインターフェースを作成するだけで以下のメソッドが起動時に自動的に作成される
  //findOne
  //findAll
  //save
  //delete

  //get by userId userRoomRelation
  List<TUserRoom> findByUserRoomRelationIdUserUserId(Integer userId);

  //get by roomId userRoomRelationId
  List<TUserRoom> findByUserRoomRelationIdRoomRoomId(Integer messageId);

  //get by userId and userRoomRelationId
  Optional<TUserRoom> findByUserRoomRelationId(UserRoomRelationId userRoomRelationId);
}
