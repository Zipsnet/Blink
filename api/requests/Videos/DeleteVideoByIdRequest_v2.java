package com.immediasemi.blink.api.requests.Videos;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class DeleteVideoByIdRequest_v2
  extends BlinkRequest
{
  private static final String path = "/api/v2/video/:video/delete";
  private static final long serialVersionUID = -7722928653918222925L;
  
  public String getPath()
  {
    return "/api/v2/video/:video/delete";
  }
  
  public int getRequestType()
  {
    return 1;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Videos/DeleteVideoByIdRequest_v2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */