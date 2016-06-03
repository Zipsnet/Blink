package com.immediasemi.blink.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

class StrictLineReader
  implements Closeable
{
  private static final byte CR = 13;
  private static final byte LF = 10;
  private byte[] buf;
  private final Charset charset;
  private int end;
  private final InputStream in;
  private int pos;
  
  public StrictLineReader(InputStream paramInputStream, int paramInt, Charset paramCharset)
  {
    if ((paramInputStream == null) || (paramCharset == null)) {
      throw new NullPointerException();
    }
    if (paramInt < 0) {
      throw new IllegalArgumentException("capacity <= 0");
    }
    if (!paramCharset.equals(Util.US_ASCII)) {
      throw new IllegalArgumentException("Unsupported encoding");
    }
    this.in = paramInputStream;
    this.charset = paramCharset;
    this.buf = new byte[paramInt];
  }
  
  public StrictLineReader(InputStream paramInputStream, Charset paramCharset)
  {
    this(paramInputStream, 8192, paramCharset);
  }
  
  private void fillBuf()
    throws IOException
  {
    int i = this.in.read(this.buf, 0, this.buf.length);
    if (i == -1) {
      throw new EOFException();
    }
    this.pos = 0;
    this.end = i;
  }
  
  public void close()
    throws IOException
  {
    synchronized (this.in)
    {
      if (this.buf != null)
      {
        this.buf = null;
        this.in.close();
      }
      return;
    }
  }
  
  public boolean hasUnterminatedLine()
  {
    if (this.end == -1) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String readLine()
    throws IOException
  {
    synchronized (this.in)
    {
      if (this.buf == null)
      {
        IOException localIOException = new java/io/IOException;
        localIOException.<init>("LineReader is closed");
        throw localIOException;
      }
    }
    if (this.pos >= this.end) {
      fillBuf();
    }
    for (int i = this.pos; i != this.end; i++) {
      if (this.buf[i] == 10)
      {
        if ((i != this.pos) && (this.buf[(i - 1)] == 13)) {}
        for (int j = i - 1;; j = i)
        {
          localObject2 = new java/lang/String;
          ((String)localObject2).<init>(this.buf, this.pos, j - this.pos, this.charset.name());
          this.pos = (i + 1);
          return (String)localObject2;
        }
      }
    }
    Object localObject2 = new com/immediasemi/blink/utils/StrictLineReader$1;
    ((1)localObject2).<init>(this, this.end - this.pos + 80);
    label273:
    for (;;)
    {
      ((ByteArrayOutputStream)localObject2).write(this.buf, this.pos, this.end - this.pos);
      this.end = -1;
      fillBuf();
      for (i = this.pos;; i++)
      {
        if (i == this.end) {
          break label273;
        }
        if (this.buf[i] == 10)
        {
          if (i != this.pos) {
            ((ByteArrayOutputStream)localObject2).write(this.buf, this.pos, i - this.pos);
          }
          this.pos = (i + 1);
          localObject2 = ((ByteArrayOutputStream)localObject2).toString();
          break;
        }
      }
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/StrictLineReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */