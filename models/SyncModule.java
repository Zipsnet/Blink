package com.immediasemi.blink.models;

public class SyncModule
  extends Device
{
  private static final long serialVersionUID = -4405103070833448169L;
  protected int account_id;
  protected String created_at;
  protected String deleted_at;
  protected int id;
  protected String last_hb;
  protected int lfr_frequency;
  protected String mac_address;
  protected String name;
  protected int network_id;
  protected String server;
  protected Enum status;
  protected String updated_at;
  
  public int getAccount_id()
  {
    return this.account_id;
  }
  
  public String getCreated_at()
  {
    return this.created_at;
  }
  
  public String getDeleted_at()
  {
    return this.deleted_at;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getLast_hb()
  {
    return this.last_hb;
  }
  
  public int getLfr_frequency()
  {
    return this.lfr_frequency;
  }
  
  public String getMac_address()
  {
    return this.mac_address;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getNetwork_id()
  {
    return this.network_id;
  }
  
  public String getServer()
  {
    return this.server;
  }
  
  public Enum getStatus()
  {
    return this.status;
  }
  
  public String getUpdated_at()
  {
    return this.updated_at;
  }
  
  public void setAccount_id(int paramInt)
  {
    this.account_id = paramInt;
  }
  
  public void setCreated_at(String paramString)
  {
    this.created_at = paramString;
  }
  
  public void setDeleted_at(String paramString)
  {
    this.deleted_at = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setLast_hb(String paramString)
  {
    this.last_hb = paramString;
  }
  
  public void setLfr_frequency(int paramInt)
  {
    this.lfr_frequency = paramInt;
  }
  
  public void setMac_address(String paramString)
  {
    this.mac_address = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setNetwork_id(int paramInt)
  {
    this.network_id = paramInt;
  }
  
  public void setServer(String paramString)
  {
    this.server = paramString;
  }
  
  public void setStatus(Enum paramEnum)
  {
    this.status = paramEnum;
  }
  
  public void setUpdated_at(String paramString)
  {
    this.updated_at = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/models/SyncModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */