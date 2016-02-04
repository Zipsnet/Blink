package com.immediasemi.blink.models;

public class AuthToken
  extends BlinkData
{
  private static final long serialVersionUID = 3815636723477554331L;
  protected AuthTokenContents authtoken;
  
  public AuthTokenContents getAuthtoken()
  {
    return this.authtoken;
  }
  
  public void setAuthtoken(AuthTokenContents paramAuthTokenContents)
  {
    this.authtoken = paramAuthTokenContents;
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


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/models/AuthToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */