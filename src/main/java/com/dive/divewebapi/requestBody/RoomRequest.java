package com.dive.divewebapi.requestBody;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequest {


    //TODO:サムネイル画像は先にアップロード
    //↓
    // アップロードした画像名とURLをbodyとしてDBに保存する
    //以上のことから、画面上で部屋を作成した際に、以下のように行なう
    //➀画像をアップロード選択+部屋情報入力
    //➁画像をS3などの場所へ登録→ImageControllerへPOSTリクエスト(bodyはファイル名のみ)
    //➂登録した画像のIDを新しく作成する部屋に登録
  // private String imageName;
  // private String imageUrl;

  private Integer roomCreaterId;

  private String roomName;

  private String roomDescription;

  public RoomRequest(
    // String imageName,
    // String imageUrl,
    Integer roomCreaterId,
    String  roomName,
    String  roomDescription
  ){
    super();
    // this.setImageName(imageName);
    // this.setImageName(imageUrl);
    this.setRoomCreaterId(roomCreaterId);
    this.setRoomName(roomName);
    this.setRoomDescription(roomDescription);
  }

}


