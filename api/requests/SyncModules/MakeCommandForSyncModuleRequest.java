package com.immediasemi.blink.api.requests.SyncModules;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class MakeCommandForSyncModuleRequest
  extends BlinkRequest
{
  private static final String path = "GetSyncModuleConfigRequest";
  private static final long serialVersionUID = -1356580229238038075L;
  
  public String getPath()
  {
    return "GetSyncModuleConfigRequest";
  }
  
  public int getRequestType()
  {
    return 1;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/SyncModules/MakeCommandForSyncModuleRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */