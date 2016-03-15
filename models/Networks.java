package com.immediasemi.blink.models;

public class Networks
  extends BlinkData
{
  private static final long serialVersionUID = 312611020342093919L;
  protected Network[] networks;
  
  public Network[] getNetworks()
  {
    return this.networks;
  }
  
  public void setNetworks(Network[] paramArrayOfNetwork)
  {
    this.networks = paramArrayOfNetwork;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/models/Networks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */