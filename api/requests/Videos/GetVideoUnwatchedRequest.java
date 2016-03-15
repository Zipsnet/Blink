package com.immediasemi.blink.api.requests.Videos;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class GetVideoUnwatchedRequest
  extends BlinkRequest
{
  private static final String path = "/videos/unwatched";
  private static final long serialVersionUID = 6041149582760854509L;
  
  public String getPath()
  {
    return "/videos/unwatched";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Videos/GetVideoUnwatchedRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */