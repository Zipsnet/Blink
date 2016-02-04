package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class DeleteCameraVideoByIdRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/video/:video/delete";
  private static final long serialVersionUID = 4798518064985445211L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/video/:video/delete";
  }
  
  public int getRequestType()
  {
    return 1;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Cameras/DeleteCameraVideoByIdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */