package com.immediasemi.blink.api.requests.SyncModules;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class UpdateSyncModule
  extends BlinkRequest
{
  private static final String path = "/network/:network/syncmodule/:syncmodule/update";
  private static final long serialVersionUID = -4504659889851991684L;
  
  public String getPath()
  {
    return "/network/:network/syncmodule/:syncmodule/update";
  }
  
  public int getRequestType()
  {
    return 1;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/SyncModules/UpdateSyncModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */