package com.immediasemi.blink.models;

import android.graphics.Bitmap;

public class BlinkBitmap
  extends BlinkData
{
  private static final long serialVersionUID = 5133548159487012009L;
  private Bitmap bitmap;
  
  public BlinkBitmap() {}
  
  public BlinkBitmap(Bitmap paramBitmap)
  {
    this.bitmap = paramBitmap;
  }
  
  public Bitmap getBitmap()
  {
    return this.bitmap;
  }
  
  public void setBitmap(Bitmap paramBitmap)
  {
    this.bitmap = paramBitmap;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/BlinkBitmap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */