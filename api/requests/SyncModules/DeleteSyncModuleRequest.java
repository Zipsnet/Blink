package com.immediasemi.blink.api.requests.SyncModules;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class DeleteSyncModuleRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/syncmodule/:syncmodule/delete/";
  private static final long serialVersionUID = -1432782504731038991L;
  
  public String getPath()
  {
    return "/network/:network/syncmodule/:syncmodule/delete/";
  }
  
  public int getRequestType()
  {
    return 1;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/SyncModules/DeleteSyncModuleRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */