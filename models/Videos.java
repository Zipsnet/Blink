package com.immediasemi.blink.models;

public class Videos
  extends BlinkData
{
  private static final long serialVersionUID = -7098428197601231670L;
  protected Video[] video;
  
  public Video[] getVideos()
  {
    return this.video;
  }
  
  public void setVideos(Video[] paramArrayOfVideo)
  {
    this.video = paramArrayOfVideo;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/Videos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */