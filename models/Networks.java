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


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/Networks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */