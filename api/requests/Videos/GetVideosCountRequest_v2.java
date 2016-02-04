package com.immediasemi.blink.api.requests.Videos;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Count;

public class GetVideosCountRequest_v2
  extends BlinkRequest
{
  private static final String path = "/api/v2/videos/count";
  private static final long serialVersionUID = -6858845629583740599L;
  
  public String getPath()
  {
    return "/api/v2/videos/count";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return Count.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Videos/GetVideosCountRequest_v2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */