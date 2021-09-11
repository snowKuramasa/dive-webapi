package com.dive.divewebapi.exception;

public class RoomNotFoundException extends Exception{
    //シリアライズ可能なクラスとしてこのフィールドを持つようにすると良い
    private static final long serialVersionUID = 1L;

    private String message;

    public void setRoom(String message) {
        this.message = message ;
    }

    public String getRoom() {
      return this.message;
  }

}
