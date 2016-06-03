package com.immediasemi.blink.api.requests.Cameras;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class DeleteCameraRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/camera/:camera/delete";
  private static final long serialVersionUID = 1480281646858914352L;
  protected Integer id;
  protected Integer network;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public Integer getNetwork()
  {
    return this.network;
  }
  
  public String getPath()
  {
    return "/network/:network/camera/:camera/delete";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public void setId(Integer paramInteger)
  {
    this.id = paramInteger;
  }
  
  public void setNetwork(Integer paramInteger)
  {
    this.network = paramInteger;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Cameras/DeleteCameraRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */