package com.immediasemi.blink.models;

public class QuickSyncModuleInfo
  extends BlinkData
{
  private static final long serialVersionUID = 123888500915676045L;
  protected String created_at;
  protected int id;
  protected boolean onboarded;
  protected String status;
  protected String updated_at;
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/QuickSyncModuleInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */