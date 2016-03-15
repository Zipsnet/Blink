package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.SignalStrength;

public class GetCameraSignalStrength
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/signals";
  private static final long serialVersionUID = 523244743363499903L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/signals";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return SignalStrength.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Cameras/GetCameraSignalStrength.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */