package com.immediasemi.blink.models;

public class HomescreenSummaryStatus
  extends BlinkData
{
  private static final long serialVersionUID = 6826039991018910386L;
  protected QuickDeviceStatus[] devices;
  protected QuickNetworkStatus network;
  
  public QuickDeviceStatus[] getDevices()
  {
    return this.devices;
  }
  
  public QuickNetworkStatus getNetwork()
  {
    return this.network;
  }
  
  public void setDevices(QuickDeviceStatus[] paramArrayOfQuickDeviceStatus)
  {
    this.devices = paramArrayOfQuickDeviceStatus;
  }
  
  public void setNetwork(QuickNetworkStatus paramQuickNetworkStatus)
  {
    this.network = paramQuickNetworkStatus;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/HomescreenSummaryStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */