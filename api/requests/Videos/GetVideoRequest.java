package com.immediasemi.blink.api.requests.Videos;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Video;

public class GetVideoRequest
  extends BlinkRequest
{
  private static final String path = "/video/:video";
  private static final long serialVersionUID = 9100072974276983933L;
  
  public String getPath()
  {
    return "/video/:video";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return Video.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Videos/GetVideoRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */