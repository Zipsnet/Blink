package com.immediasemi.blink.api.requests.Videos;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Videos;

public class GetVideosRequest_v2
  extends BlinkRequest
{
  private static final String path = "/api/v2/videos/page/:page";
  private static final long serialVersionUID = 5267784990966361077L;
  
  public String getPath()
  {
    return "/api/v2/videos/page/:page";
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


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Videos/GetVideosRequest_v2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */