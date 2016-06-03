package com.immediasemi.blink.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.BlinkImageRequest;
import com.immediasemi.blink.models.BlinkBitmap;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public class ImageLoader
  implements Closeable
{
  int borderWidth;
  boolean closed;
  ImageView imgView;
  boolean roundedCorners;
  
  public ImageLoader(String paramString, ImageView paramImageView, boolean paramBoolean, int paramInt)
  {
    this.roundedCorners = paramBoolean;
    loadImage(paramString, paramImageView, paramInt);
  }
  
  public static Bitmap getRoundedCornerBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, Context paramContext)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    paramInt3 = (int)TypedValue.applyDimension(1, paramInt3, paramContext.getResources().getDisplayMetrics());
    paramInt2 = (int)TypedValue.applyDimension(1, paramInt2, paramContext.getResources().getDisplayMetrics());
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    paramContext = new RectF(localRect);
    localPaint.setAntiAlias(true);
    localPaint.setColor(-1);
    localPaint.setStyle(Paint.Style.FILL);
    localCanvas.drawARGB(0, 0, 0, 0);
    localCanvas.drawRoundRect(paramContext, paramInt2, paramInt2, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    localPaint.setColor(paramInt1);
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setStrokeWidth(paramInt3);
    localCanvas.drawRoundRect(paramContext, paramInt2, paramInt2, localPaint);
    return localBitmap;
  }
  
  private void loadImage(String paramString, ImageView paramImageView, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      this.borderWidth = 6;
    }
    for (;;)
    {
      str = Integer.toHexString(paramString.hashCode());
      this.imgView = paramImageView;
      Object localObject = null;
      for (;;)
      {
        try
        {
          localSnapshot = BlinkApp.getApp().getDiskLruCache().get(str);
          paramImageView = (ImageView)localObject;
          if (localSnapshot == null) {}
        }
        catch (Exception paramString)
        {
          DiskLruCache.Snapshot localSnapshot;
          paramString.printStackTrace();
          continue;
          paramImageView = new com/immediasemi/blink/utils/ImageLoader$1;
          paramImageView.<init>(this, str, paramString);
          new BlinkImageRequest(paramString, paramImageView, 0, 0, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.ARGB_8888);
          continue;
        }
        try
        {
          paramImageView = BitmapFactory.decodeStream(localSnapshot.getInputStream(1));
          if ((paramImageView != null) && (!paramImageView.isRecycled()))
          {
            this.imgView.setVisibility(0);
            setImageBitmap(this.imgView, paramImageView);
            return;
            paramString = paramString + "_s";
            this.borderWidth = 1;
            break;
            paramString = paramString + "_m";
            this.borderWidth = 4;
          }
        }
        catch (Exception paramImageView)
        {
          paramImageView.printStackTrace();
          paramImageView = (ImageView)localObject;
        }
      }
    }
  }
  
  private void setImageBitmap(ImageView paramImageView, Bitmap paramBitmap)
  {
    if (this.roundedCorners)
    {
      paramBitmap = getRoundedCornerBitmap(paramBitmap, BlinkApp.getApp().getResources().getColor(2131492875), 8, this.borderWidth, BlinkApp.getApp().getApplicationContext());
      paramImageView.setAdjustViewBounds(true);
      paramImageView.setImageBitmap(paramBitmap);
    }
    for (;;)
    {
      return;
      paramImageView.setImageBitmap(paramBitmap);
    }
  }
  
  public void close()
    throws IOException
  {
    this.closed = true;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/ImageLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */