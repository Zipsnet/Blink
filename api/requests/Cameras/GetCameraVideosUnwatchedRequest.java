package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class GetCameraVideosUnwatchedRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/videos/unwatched";
  private static final long serialVersionUID = -5167483718722706693L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/videos/unwatched";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Cameras/GetCameraVideosUnwatchedRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */