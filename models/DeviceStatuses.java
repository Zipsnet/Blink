package com.immediasemi.blink.models;

public class DeviceStatuses
  extends BlinkData
{
  private static final long serialVersionUID = 3221662855332845577L;
  protected DeviceStatus[] devicestatus;
  
  public DeviceStatus[] getDevicestatus()
  {
    return this.devicestatus;
  }
  
  public void setDevicestatus(DeviceStatus[] paramArrayOfDeviceStatus)
  {
    this.devicestatus = paramArrayOfDeviceStatus;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/DeviceStatuses.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */