package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.CameraStatus;

public class GetSingleCameraStatusRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera";
  private static final long serialVersionUID = -4177683129247429060L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return CameraStatus.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Cameras/GetSingleCameraStatusRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */