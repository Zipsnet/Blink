package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class DisableLiveViewRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/liveview/disable";
  private static final long serialVersionUID = -6131872282921930939L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/liveview/disable";
  }
  
  public int getRequestType()
  {
    return 1;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Cameras/DisableLiveViewRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */