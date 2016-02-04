package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Command;

public class RefreshCameraRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/status";
  private static final long serialVersionUID = 997577207994066932L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/status";
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


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Cameras/RefreshCameraRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */