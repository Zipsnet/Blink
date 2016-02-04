package com.immediasemi.blink.api.requests.SyncModules;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Command;

public class ResetSyncModuleRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/syncmodule/:syncmodule/sm_reset";
  private static final long serialVersionUID = -8566594313816188423L;
  
  public String getPath()
  {
    return "/network/:network/syncmodule/:syncmodule/sm_reset";
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


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/SyncModules/ResetSyncModuleRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */