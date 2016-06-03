package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Videos;

public class GetCameraVideosRequest_v2
  extends BlinkRequest
{
  private static final String path = "/api/v2/network/:network/camera/:camera/videos/page/:page";
  private static final long serialVersionUID = -4328165270565359600L;
  
  public String getPath()
  {
    return "/api/v2/network/:network/camera/:camera/videos/page/:page";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return Videos.class;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Cameras/GetCameraVideosRequest_v2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */