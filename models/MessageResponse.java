package com.immediasemi.blink.models;

public class MessageResponse
  extends BlinkData
{
  private static final long serialVersionUID = 5666926222886690255L;
  protected String message;
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/models/MessageResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */