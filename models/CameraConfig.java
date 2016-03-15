package com.immediasemi.blink.models;

public class CameraConfig
  extends BlinkData
{
  private static final long serialVersionUID = -9018191751718611034L;
  protected CameraConfigInfo[] camera;
  
  public CameraConfigInfo[] getCameraConfig()
  {
    return this.camera;
  }
  
  public void setCameraConfig(CameraConfigInfo[] paramArrayOfCameraConfigInfo)
  {
    this.camera = paramArrayOfCameraConfigInfo;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/models/CameraConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */