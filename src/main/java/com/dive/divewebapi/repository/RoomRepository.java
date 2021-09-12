package com.dive.divewebapi.repository;

import java.util.List;

import com.dive.divewebapi.entity.TImage;
import com.dive.divewebapi.entity.TRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<TRoom, Integer> {

  //JpaRepositoryには基本的なCRUDメソッドが定義されているため、
  //このインターフェースを作成するだけで以下のメソッドが起動時に自動的に作成される
  //findOne
  //findAll
  //save
  //delete
  //NOTE:JpaRepositoryで定義されていないような検索や処理はJPQLでクエリを定義して対応する

  //#region get joincolumn

  //roomCreater
  List<TRoom> findByRoomCreaterUserId(Integer roomCreaterId);

  //roomName(search wild card)
  List<TRoom> findByRoomNameContaining(String roomName);

  //thumbnail
  List<TImage> findByThumbnailImageId(Integer thumbnailId);

  //#endregion get joincolumn
}
