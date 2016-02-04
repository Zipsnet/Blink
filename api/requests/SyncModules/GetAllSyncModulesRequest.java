package com.immediasemi.blink.api.requests.SyncModules;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.SyncModules;

public class GetAllSyncModulesRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/syncmodules";
  private static final long serialVersionUID = -1502933139855714882L;
  
  public String getPath()
  {
    return "/network/:network/syncmodules";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return SyncModules.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/SyncModules/GetAllSyncModulesRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */