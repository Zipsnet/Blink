package com.immediasemi.blink.api.requests.Accounts;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.MessageResponse;

public class ResetPasswordRequest
  extends BlinkRequest
{
  private static final String path = "/account/reset_password";
  private static final long serialVersionUID = 1913256851342064642L;
  protected String email;
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getPath()
  {
    return "/account/reset_password";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return MessageResponse.class;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Accounts/ResetPasswordRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */