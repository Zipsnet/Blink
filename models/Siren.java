package com.immediasemi.blink.models;

public class Siren
  extends Device
{
  private static final long serialVersionUID = 31804168924405632L;
  protected int account_id;
  protected String created_at;
  protected String deleted_at;
  protected boolean enabled;
  protected int id;
  protected String mac_address;
  protected String name;
  protected int network_id;
  protected int sync_module;
  protected int unit_number;
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
  
  public int getSync_module()
  {
    return this.sync_module;
  }
  
  public int getUnit_number()
  {
    return this.unit_number;
  }
  
  public String getUpdated_at()
  {
    return this.updated_at;
  }
  
  public boolean isEnabled()
  {
    return this.enabled;
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
  
  public void setEnabled(boolean paramBoolean)
  {
    this.enabled = paramBoolean;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
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
  
  public void setSync_module(int paramInt)
  {
    this.sync_module = paramInt;
  }
  
  public void setUnit_number(int paramInt)
  {
    this.unit_number = paramInt;
  }
  
  public void setUpdated_at(String paramString)
  {
    this.updated_at = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/Siren.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */