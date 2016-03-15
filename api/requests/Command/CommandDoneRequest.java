package com.immediasemi.blink.api.requests.Command;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.MessageResponse;

public class CommandDoneRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/command/:command/done";
  private static final long serialVersionUID = -8677522816381774297L;
  
  public String getPath()
  {
    return "/network/:network/command/:command/done";
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


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Command/CommandDoneRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */