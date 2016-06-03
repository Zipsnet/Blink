package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Command;

public class WakeUpCameraRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/clip";
  private static final long serialVersionUID = -4636130914369248924L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/clip";
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


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Cameras/WakeUpCameraRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */