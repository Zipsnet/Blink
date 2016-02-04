package com.immediasemi.blink.api.requests.Clients;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class UpdateClientRequest
  extends BlinkRequest
{
  private static final String path = "/client/update";
  private static final long serialVersionUID = 6897530805127301969L;
  protected String client_specifier;
  protected String client_type;
  protected String name;
  
  public String getClient_specifier()
  {
    return this.client_specifier;
  }
  
  public String getClient_type()
  {
    return this.client_type;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPath()
  {
    return "/client/update";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public void setClient_specifier(String paramString)
  {
    this.client_specifier = paramString;
  }
  
  public void setClient_type(String paramString)
  {
    this.client_type = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Clients/UpdateClientRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */