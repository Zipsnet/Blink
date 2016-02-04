package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.DeviceStatuses;

public class GetAllCamerasStatusRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/cameras";
  private static final long serialVersionUID = 5061376298425467269L;
  
  public String getPath()
  {
    return "/network/:network/cameras";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return DeviceStatuses.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Cameras/GetAllCamerasStatusRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */