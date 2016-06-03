package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class GetCameraVideosCountRequest_v2
  extends BlinkRequest
{
  private static final String path = "/api/v2/network/:network/camera/:camera/videos/count";
  private static final long serialVersionUID = 1259310606398916633L;
  
  public String getPath()
  {
    return "/api/v2/network/:network/camera/:camera/videos/count";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Cameras/GetCameraVideosCountRequest_v2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */