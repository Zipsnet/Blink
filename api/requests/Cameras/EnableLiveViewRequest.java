package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.LiveVideoResponse;

public class EnableLiveViewRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/liveview";
  private static final long serialVersionUID = -6622171850984898445L;
  protected Integer id;
  protected Integer network;
  protected Integer type;
  
  public int getId()
  {
    return this.id.intValue();
  }
  
  public int getNetwork()
  {
    return this.network.intValue();
  }
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/liveview";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return LiveVideoResponse.class;
  }
  
  public int getType()
  {
    return this.type.intValue();
  }
  
  public void setId(int paramInt)
  {
    this.id = Integer.valueOf(paramInt);
  }
  
  public void setNetwork(int paramInt)
  {
    this.network = Integer.valueOf(paramInt);
  }
  
  public void setType(int paramInt)
  {
    this.type = Integer.valueOf(paramInt);
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Cameras/EnableLiveViewRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */