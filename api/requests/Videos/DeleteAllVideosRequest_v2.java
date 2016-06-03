package com.immediasemi.blink.api.requests.Videos;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.MessageResponse;

public class DeleteAllVideosRequest_v2
  extends BlinkRequest
{
  private static final String path = "/api/v2/videos/delete_all";
  private static final long serialVersionUID = 5923225066855094795L;
  
  public String getPath()
  {
    return "/api/v2/videos/delete_all";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return MessageResponse.class;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Videos/DeleteAllVideosRequest_v2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */