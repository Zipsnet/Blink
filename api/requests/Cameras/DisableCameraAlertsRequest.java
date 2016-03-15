package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Command;

public class DisableCameraAlertsRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/disable";
  private static final long serialVersionUID = -6073951095816199747L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/disable";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return Command.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Cameras/DisableCameraAlertsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */