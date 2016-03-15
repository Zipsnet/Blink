package com.immediasemi.blink.api.requests.Accounts;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.AccountRetrievalResponse;

public class RetreivalRequest
  extends BlinkRequest
{
  private static final String path = "/account";
  private static final long serialVersionUID = 1755720632863728368L;
  
  public String getPath()
  {
    return "/account";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return AccountRetrievalResponse.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Accounts/RetreivalRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */