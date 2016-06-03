package com.immediasemi.blink.utils;

public class Validation
{
  public static boolean validateEmail(String paramString)
  {
    String str3 = "[" + "a-zA-Z" + "0-9" + "\\!\\#\\$\\%\\&\\'\\*+\\/\\=\\?\\^\\_\\`\\{\\|\\}\\~\\-]";
    String str1 = str3 + "+([.]" + str3 + "*)+";
    String str4 = "[^" + "\\x01-\\x08\\x11\\x12\\x14-\\x1f\\x7f" + "\\x0d\\x22\\x5c]";
    String str2 = "(\\x5c" + "[\\x01-\\x09\\x11\\x12\\x14-\\x7f]" + ")";
    str4 = "(?:" + str4 + "|" + str2 + ")";
    str4 = "[\"]" + str4 + "+[\"]";
    str3 = str3 + "+";
    String str5 = "(?:" + str3 + "|" + str4 + ")";
    str5 = str5 + "([.]" + str5 + ")*";
    str4 = "(?:" + str1 + "|" + str4 + "|" + str5 + ")";
    str5 = "[" + "\\x01-\\x08\\x11\\x12\\x14-\\x1f\\x7f" + "\\x21-\\x5a\\x5e-\\x7e]";
    str2 = "(?:" + str5 + "|" + str2 + ")";
    str2 = "\\[" + str2 + "+\\]";
    str3 = str3 + "([.]" + str3 + ")+";
    str1 = "(?:" + str1 + "|" + str2 + "|" + str3 + ")";
    return paramString.replaceFirst(str4 + "\\@" + str1, "").isEmpty();
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/Validation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */