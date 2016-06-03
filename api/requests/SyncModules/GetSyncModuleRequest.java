package com.immediasemi.blink.api.requests.SyncModules;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class GetSyncModuleRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/syncmodule/:syncmodule/";
  private static final long serialVersionUID = -975711812207470680L;
  
  public String getPath()
  {
    return "/network/:network/syncmodule/:syncmodule/";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/SyncModules/GetSyncModuleRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */