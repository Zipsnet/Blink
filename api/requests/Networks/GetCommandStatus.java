package com.immediasemi.blink.api.requests.Networks;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Commands;

public class GetCommandStatus
  extends BlinkRequest
{
  private static final String path = "/network/:network/command/:command";
  private static final long serialVersionUID = 8547520309797372892L;
  
  public String getPath()
  {
    return "/network/:network/command/:command";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return Commands.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Networks/GetCommandStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */