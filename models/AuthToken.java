package com.immediasemi.blink.models;

import java.util.HashMap;

public class AuthToken
  extends BlinkData
{
  private static final long serialVersionUID = 3815636723477554331L;
  protected AuthTokenContents authtoken;
  protected HashMap<String, String> region;
  
  public AuthTokenContents getAuthtoken()
  {
    return this.authtoken;
  }
  
  public HashMap<String, String> getRegion()
  {
    return this.region;
  }
  
  public void setAuthtoken(AuthTokenContents paramAuthTokenContents)
  {
    this.authtoken = paramAuthTokenContents;
  }
  
  public void setRegion(HashMap<String, String> paramHashMap)
  {
    this.region = paramHashMap;
  }
  
  public class AuthTokenContents
  {
    protected String authtoken;
    protected String message;
    
    public AuthTokenContents() {}
    
    public String getAuthtoken()
    {
      return this.authtoken;
    }
    
    public String getMessage()
    {
      return this.message;
    }
    
    public void setAuthtoken(String paramString)
    {
      this.authtoken = paramString;
    }
    
    public void setMessage(String paramString)
    {
      this.message = paramString;
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/AuthToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */