package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.OneCamera;

public class AddCameraRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/add";
  private static final long serialVersionUID = 1086854505018086222L;
  protected Integer network;
  protected String serial;
  
  public int getNetwork()
  {
    return this.network.intValue();
  }
  
  public String getPath()
  {
    return "/network/:network/camera/add";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return OneCamera.class;
  }
  
  public String getSerial()
  {
    return this.serial;
  }
  
  public void setNetwork(int paramInt)
  {
    this.network = Integer.valueOf(paramInt);
  }
  
  public void setSerial(String paramString)
  {
    this.serial = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Cameras/AddCameraRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */