package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.immediasemi.blink.activities.BaseActivity;

public class VisitBlinkFragment
  extends BaseFragment
{
  public static String TITLE_STRING = "title_string";
  public static String URL_STRING = "url_string";
  private int mSectionNumber;
  private String mTitle;
  private String mUrl;
  private WebView mVisitBlinkView;
  
  public static VisitBlinkFragment newInstance(int paramInt, String paramString1, String paramString2)
  {
    VisitBlinkFragment localVisitBlinkFragment = new VisitBlinkFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localBundle.putString(URL_STRING, paramString1);
    localBundle.putString(TITLE_STRING, paramString2);
    localVisitBlinkFragment.setArguments(localBundle);
    return localVisitBlinkFragment;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(this.mTitle);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null)
    {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
      this.mUrl = getArguments().getString(URL_STRING);
      this.mTitle = getArguments().getString(TITLE_STRING);
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903115, paramViewGroup, false);
    this.mVisitBlinkView = ((WebView)paramLayoutInflater.findViewById(2131558687));
    this.mVisitBlinkView.setWebViewClient(new WebViewClient());
    this.mVisitBlinkView.loadUrl(this.mUrl);
    if (getActivity() != null) {
      ((BaseActivity)getActivity()).setActionBarTitle(this.mTitle);
    }
    return paramLayoutInflater;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/fragments/VisitBlinkFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */