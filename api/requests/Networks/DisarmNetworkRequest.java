package com.immediasemi.blink.api.requests.Networks;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Command;

public class DisarmNetworkRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/disarm";
  private static final long serialVersionUID = 1250241836554869461L;
  
  public String getPath()
  {
    return "/network/:network/disarm";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return Command.class;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Networks/DisarmNetworkRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */