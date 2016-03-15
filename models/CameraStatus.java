package com.immediasemi.blink.models;

public class CameraStatus
  extends BlinkData
{
  private static final long serialVersionUID = 1099402727468243814L;
  protected DeviceStatus camera_status;
  
  public DeviceStatus getCamera_status()
  {
    return this.camera_status;
  }
  
  public void setCamera_status(DeviceStatus paramDeviceStatus)
  {
    this.camera_status = paramDeviceStatus;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/models/CameraStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */