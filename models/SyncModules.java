package com.immediasemi.blink.models;

public class SyncModules
  extends BlinkData
{
  private static final long serialVersionUID = 576611020342093919L;
  protected QuickSyncModuleInfo syncmodule;
  
  public QuickSyncModuleInfo getSyncmodule()
  {
    return this.syncmodule;
  }
  
  public void setSyncmodule(QuickSyncModuleInfo paramQuickSyncModuleInfo)
  {
    this.syncmodule = paramQuickSyncModuleInfo;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/SyncModules.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */