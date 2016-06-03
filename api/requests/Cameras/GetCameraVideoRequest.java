package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Video;

public class GetCameraVideoRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/video/:video/";
  private static final long serialVersionUID = 7055861246127014949L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/video/:video/";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return Video.class;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Cameras/GetCameraVideoRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */