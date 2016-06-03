package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Command;

public class TakeSnapshotRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/thumbnail";
  private static final long serialVersionUID = 4424571475653843380L;
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/thumbnail";
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


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Cameras/TakeSnapshotRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */