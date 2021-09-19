package com.dive.divewebapi.exception;

public class FavoriteNotSaveException extends Exception{
   //シリアライズ可能なクラスとしてこのフィールドを持つようにすると良い
    private static final long serialVersionUID = 1L;

    private String message;

    public void setMessage(String message) {
        this.message = message ;
    }

    public String getMessage() {
      return this.message;
  }
}
