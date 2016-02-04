package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.CameraConfig;

public class GetCameraInfoRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/config";
  private static final long serialVersionUID = 1001773780304999602L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/config";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return CameraConfig.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Cameras/GetCameraInfoRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */