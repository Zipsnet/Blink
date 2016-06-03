package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class UpdateCameraVideoRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/video/:video/update";
  private static final long serialVersionUID = -4632538957961228245L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/video/:video/update";
  }
  
  public int getRequestType()
  {
    return 1;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Cameras/UpdateCameraVideoRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */