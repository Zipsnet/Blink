package com.immediasemi.blink.api.requests.SyncModules;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class GetSyncModuleConfigRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/syncmodule/:syncmodule/config";
  private static final long serialVersionUID = -3452602973545524969L;
  
  public String getPath()
  {
    return "/network/:network/syncmodule/:syncmodule/config";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/SyncModules/GetSyncModuleConfigRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */