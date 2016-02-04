package com.immediasemi.blink.api.requests.Networks;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class GetNetworkVideosUnwatchedRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/videos/unwatched";
  private static final long serialVersionUID = 240573863990372243L;
  
  public String getPath()
  {
    return "/network/:network/videos/unwatched";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Networks/GetNetworkVideosUnwatchedRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */