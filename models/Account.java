package com.immediasemi.blink.models;

public class Account
  extends BlinkData
{
  private static final long serialVersionUID = 2965348470681369528L;
  protected String created_at;
  protected String deleted_at;
  protected int id;
  protected String phone_number;
  protected String updated_at;
  
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
  
  public String getPhone_number()
  {
    return this.phone_number;
  }
  
  public String getUpdated_at()
  {
    return this.updated_at;
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
  
  public void setPhone_number(String paramString)
  {
    this.phone_number = paramString;
  }
  
  public void setUpdated_at(String paramString)
  {
    this.updated_at = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/Account.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */