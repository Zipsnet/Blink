package com.immediasemi.blink.utils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public final class Util
{
  static final Charset US_ASCII = Charset.forName("US-ASCII");
  static final Charset UTF_8 = Charset.forName("UTF-8");
  public static String[] mWeekdays;
  
  public static String SHA1(String paramString)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
  {
    MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
    localMessageDigest.update(paramString.getBytes("iso-8859-1"), 0, paramString.length());
    return convertToHex(localMessageDigest.digest());
  }
  
  public static String applyHash(String paramString1, String paramString2)
  {
    if (paramString2.length() == 0)
    {
      paramString1 = paramString2;
      return paramString1;
    }
    String str = paramString2;
    if (paramString2.length() > 38) {
      str = paramString2.substring(0, 38);
    }
    for (paramString2 = str; paramString2.length() < 38; paramString2 = paramString2 + paramString2) {}
    paramString2 = paramString2.substring(paramString2.length() - 38);
    if (str.length() < 10) {}
    for (paramString2 = paramString2 + "0";; paramString2 = paramString2 + String.valueOf(str.length() / 10))
    {
      paramString1 = xorHash(paramString1, convertToHex((paramString2 + String.valueOf(str.length() % 10)).getBytes()));
      break;
    }
  }
  
  static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (RuntimeException paramCloseable)
    {
      throw paramCloseable;
    }
    catch (Exception paramCloseable)
    {
      for (;;) {}
    }
  }
  
  private static String convertFromHex(String paramString)
  {
    paramString = paramString.getBytes();
    if ((paramString.length & 0x1) != 0) {}
    StringBuilder localStringBuilder;
    for (paramString = "";; paramString = localStringBuilder.toString())
    {
      return paramString;
      localStringBuilder = new StringBuilder();
      int i = 0;
      if (i < paramString.length)
      {
        int j;
        if (paramString[i] <= 57)
        {
          j = paramString[i] - 48;
          label49:
          if (paramString[(i + 1)] > 57) {
            break label106;
          }
        }
        label106:
        for (int k = paramString[(i + 1)] - 48;; k = paramString[(i + 1)] - 97 + 10)
        {
          localStringBuilder.append((char)(j << 4 & 0xF0 | k & 0xF));
          i += 2;
          break;
          j = paramString[i] - 97 + 10;
          break label49;
        }
      }
    }
  }
  
  private static String convertToHex(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int m = paramArrayOfByte.length;
    int i = 0;
    int n;
    int k;
    if (i < m)
    {
      n = paramArrayOfByte[i];
      k = n >>> 4 & 0xF;
    }
    label100:
    for (int j = 0;; j++)
    {
      if ((k >= 0) && (k <= 9)) {}
      for (char c = (char)(k + 48);; c = (char)(k - 10 + 97))
      {
        localStringBuilder.append(c);
        k = n & 0xF;
        if (j < 1) {
          break label100;
        }
        i++;
        break;
      }
      return localStringBuilder.toString();
    }
  }
  
  static void deleteContents(File paramFile)
    throws IOException
  {
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null) {
      throw new IOException("not a readable directory: " + paramFile);
    }
    int j = arrayOfFile.length;
    for (int i = 0; i < j; i++)
    {
      paramFile = arrayOfFile[i];
      if (paramFile.isDirectory()) {
        deleteContents(paramFile);
      }
      if (!paramFile.delete()) {
        throw new IOException("failed to delete file: " + paramFile);
      }
    }
  }
  
  public static String getLocalDateTime(String paramString)
  {
    Object localObject2 = new SimpleDateFormat("yyyy-MM-dd k:mm:ssZ", Locale.UK);
    Object localObject1 = new SimpleDateFormat("MMM dd h:mm:ss a", Locale.getDefault());
    paramString = paramString.replace('T', ' ');
    try
    {
      localObject2 = ((SimpleDateFormat)localObject2).parse(paramString);
      ((DateFormat)localObject1).setTimeZone(Calendar.getInstance().getTimeZone());
      localObject1 = ((DateFormat)localObject1).format((Date)localObject2);
      paramString = (String)localObject1;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return paramString;
  }
  
  public static String getLocalDateYearTime(String paramString)
  {
    Object localObject2 = new SimpleDateFormat("yyyy-MM-dd k:mm:ssZ", Locale.UK);
    Object localObject1 = new SimpleDateFormat("MMM dd yyyy h:mm:ss a", Locale.getDefault());
    paramString = paramString.replace('T', ' ');
    try
    {
      localObject2 = ((SimpleDateFormat)localObject2).parse(paramString);
      ((DateFormat)localObject1).setTimeZone(Calendar.getInstance().getTimeZone());
      localObject1 = ((DateFormat)localObject1).format((Date)localObject2);
      paramString = (String)localObject1;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return paramString;
  }
  
  public static String getTodayFormatted()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("MMM dd yyyy hh:mm:ss aa").format(localDate);
  }
  
  public static String getWeekday(int paramInt)
  {
    while (paramInt < 0) {
      paramInt += 7;
    }
    return mWeekdays[(paramInt % 7)];
  }
  
  static String readFully(Reader paramReader)
    throws IOException
  {
    try
    {
      StringWriter localStringWriter = new java/io/StringWriter;
      localStringWriter.<init>();
      char[] arrayOfChar = new char['Ѐ'];
      for (;;)
      {
        int i = paramReader.read(arrayOfChar);
        if (i == -1) {
          break;
        }
        localStringWriter.write(arrayOfChar, 0, i);
      }
      str = ((StringWriter)localObject).toString();
    }
    finally
    {
      paramReader.close();
    }
    String str;
    paramReader.close();
    return str;
  }
  
  public static String reformatDate(String paramString)
  {
    Object localObject2 = paramString.replace('T', ' ');
    Object localObject3 = new SimpleDateFormat("MMM dd yyyy hh:mm:ss aa");
    Object localObject1 = new SimpleDateFormat("h:mm aa", Locale.getDefault());
    paramString = new SimpleDateFormat("MMM dd yyyy", Locale.getDefault());
    for (;;)
    {
      try
      {
        localObject2 = ((SimpleDateFormat)localObject3).parse((String)localObject2);
        localObject1 = ((DateFormat)localObject1).format((Date)localObject2);
        localObject3 = new java/util/GregorianCalendar;
        ((GregorianCalendar)localObject3).<init>();
        ((GregorianCalendar)localObject3).setTime((Date)localObject2);
        ((GregorianCalendar)localObject3).set(10, 0);
        ((GregorianCalendar)localObject3).set(12, 0);
        ((GregorianCalendar)localObject3).set(13, 0);
        ((GregorianCalendar)localObject3).set(14, 0);
        localGregorianCalendar = new java/util/GregorianCalendar;
        localGregorianCalendar.<init>();
        localGregorianCalendar.set(10, 0);
        localGregorianCalendar.set(12, 0);
        localGregorianCalendar.set(13, 0);
        localGregorianCalendar.set(14, 0);
        if ((((GregorianCalendar)localObject3).get(1) != localGregorianCalendar.get(1)) || (((GregorianCalendar)localObject3).get(6) != localGregorianCalendar.get(6))) {
          continue;
        }
        paramString = "Today%";
        localObject2 = new java/lang/StringBuilder;
        ((StringBuilder)localObject2).<init>();
        paramString = paramString + (String)localObject1;
      }
      catch (ParseException paramString)
      {
        GregorianCalendar localGregorianCalendar;
        paramString.printStackTrace();
        paramString = "";
        continue;
      }
      return paramString;
      localGregorianCalendar.add(6, -1);
      if ((((GregorianCalendar)localObject3).get(1) == localGregorianCalendar.get(1)) && (((GregorianCalendar)localObject3).get(6) == localGregorianCalendar.get(6)))
      {
        paramString = "Yesterday%";
      }
      else
      {
        localGregorianCalendar.add(6, -6);
        if (((GregorianCalendar)localObject3).after(localGregorianCalendar))
        {
          paramString = new java/lang/StringBuilder;
          paramString.<init>();
          paramString = getWeekday(((GregorianCalendar)localObject3).get(7) - 1) + "%";
        }
        else
        {
          localObject3 = new java/lang/StringBuilder;
          ((StringBuilder)localObject3).<init>();
          paramString = paramString.format((Date)localObject2) + "%";
        }
      }
    }
  }
  
  public static String removeHash(String paramString1, String paramString2)
  {
    paramString1 = xorHash(paramString1, paramString2);
    if (paramString1.length() == 0) {
      paramString1 = "";
    }
    for (;;)
    {
      return paramString1;
      paramString1 = convertFromHex(paramString1);
      int i = Integer.valueOf(paramString1.substring(paramString1.length() - 2)).intValue();
      if ((i < 1) || (i > 38)) {
        paramString1 = "";
      } else {
        paramString1 = paramString1.substring(paramString1.length() - i - 2, paramString1.length() - 2);
      }
    }
  }
  
  private static String xorHash(String paramString1, String paramString2)
  {
    if (paramString1.length() != paramString2.length()) {}
    StringBuilder localStringBuilder;
    for (paramString1 = "";; paramString1 = localStringBuilder.toString())
    {
      return paramString1;
      localStringBuilder = new StringBuilder();
      int i = 0;
      if (i < paramString1.length())
      {
        int j;
        label58:
        int k;
        if (paramString1.charAt(i) > '9')
        {
          j = paramString1.charAt(i) - 'a' + 10;
          if (paramString2.charAt(i) <= '9') {
            break label136;
          }
          k = paramString2.charAt(i) - 'a' + 10;
          label81:
          j = (j ^ k) & 0xF;
          if ((j < 0) || (j > 9)) {
            break label149;
          }
        }
        label136:
        label149:
        for (char c = (char)(j + 48);; c = (char)(j - 10 + 97))
        {
          localStringBuilder.append(c);
          i++;
          break;
          j = paramString1.charAt(i) - '0';
          break label58;
          k = paramString2.charAt(i) - '0';
          break label81;
        }
      }
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */