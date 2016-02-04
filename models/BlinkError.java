package com.immediasemi.blink.models;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import java.io.Serializable;

public class BlinkError
  implements Serializable
{
  private static final long serialVersionUID = -4620063504420699924L;
  protected String errorMessage = null;
  
  public BlinkError() {}
  
  public BlinkError(VolleyError paramVolleyError)
  {
    String str = paramVolleyError.getClass().getSimpleName();
    NetworkResponse localNetworkResponse = paramVolleyError.networkResponse;
    try
    {
      this.errorMessage = new String(localNetworkResponse.data, "utf-8");
      if (this.errorMessage == null) {
        if (paramVolleyError.networkResponse != null) {
          this.errorMessage = (str + ": " + String.valueOf(paramVolleyError.networkResponse.statusCode));
        }
      }
      do
      {
        do
        {
          return;
          this.errorMessage = str;
          return;
        } while (!this.errorMessage.startsWith("{"));
        paramVolleyError = (MessageResponse)new Gson().fromJson(this.errorMessage, MessageResponse.class);
      } while ((paramVolleyError == null) || (paramVolleyError.getMessage() == null));
      this.errorMessage = paramVolleyError.getMessage();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public String getErrorMessage()
  {
    return this.errorMessage;
  }
  
  public void setErrorMessage(String paramString)
  {
    this.errorMessage = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/models/BlinkError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */