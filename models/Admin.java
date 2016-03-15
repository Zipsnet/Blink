package com.immediasemi.blink.models;

public class Admin
  extends BlinkData
{
  private static final long serialVersionUID = 1615456055766808425L;
  protected int admin_level;
  protected String created_at;
  protected String deleted_at;
  protected String email;
  protected String email_hash;
  protected int id;
  protected BCryptHash password;
  protected String reset_expiration;
  protected Text reset_token;
  protected String time_zone;
  protected String updated_at;
  protected boolean verified;
  protected Text verified_token;
  
  public int getAdmin_level()
  {
    return this.admin_level;
  }
  
  public String getCreated_at()
  {
    return this.created_at;
  }
  
  public String getDeleted_at()
  {
    return this.deleted_at;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getEmail_hash()
  {
    return this.email_hash;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public BCryptHash getPassword()
  {
    return this.password;
  }
  
  public String getReset_expiration()
  {
    return this.reset_expiration;
  }
  
  public Text getReset_token()
  {
    return this.reset_token;
  }
  
  public String getTime_zone()
  {
    return this.time_zone;
  }
  
  public String getUpdated_at()
  {
    return this.updated_at;
  }
  
  public Text getVerified_token()
  {
    return this.verified_token;
  }
  
  public boolean isVerified()
  {
    return this.verified;
  }
  
  public void setAdmin_level(int paramInt)
  {
    this.admin_level = paramInt;
  }
  
  public void setCreated_at(String paramString)
  {
    this.created_at = paramString;
  }
  
  public void setDeleted_at(String paramString)
  {
    this.deleted_at = paramString;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public void setEmail_hash(String paramString)
  {
    this.email_hash = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setPassword(BCryptHash paramBCryptHash)
  {
    this.password = paramBCryptHash;
  }
  
  public void setReset_expiration(String paramString)
  {
    this.reset_expiration = paramString;
  }
  
  public void setReset_token(Text paramText)
  {
    this.reset_token = paramText;
  }
  
  public void setTime_zone(String paramString)
  {
    this.time_zone = paramString;
  }
  
  public void setUpdated_at(String paramString)
  {
    this.updated_at = paramString;
  }
  
  public void setVerified(boolean paramBoolean)
  {
    this.verified = paramBoolean;
  }
  
  public void setVerified_token(Text paramText)
  {
    this.verified_token = paramText;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/models/Admin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */