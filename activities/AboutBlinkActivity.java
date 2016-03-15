package com.immediasemi.blink.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.immediasemi.blink.fragments.VisitBlinkFragment;
import com.immediasemi.blink.utils.OnClick;

public class AboutBlinkActivity
  extends BaseActivity
{
  private TextView mBuildNumber;
  private View mPrivacyPolicy;
  private View mTermsOfService;
  private TextView mVersion;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903065);
    paramBundle = (LinearLayout)findViewById(2131558526);
    ((TextView)paramBundle.findViewById(2131558530)).setText(2131099719);
    this.mVersion = ((TextView)paramBundle.findViewById(2131558531));
    paramBundle = (LinearLayout)findViewById(2131558527);
    ((TextView)paramBundle.findViewById(2131558530)).setText(2131099716);
    this.mBuildNumber = ((TextView)paramBundle.findViewById(2131558531));
    this.mTermsOfService = findViewById(2131558528);
    ((TextView)this.mTermsOfService.findViewById(2131558530)).setText(2131099718);
    ((TextView)this.mTermsOfService.findViewById(2131558531)).setText(">");
    this.mTermsOfService.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        paramAnonymousView = new Intent(AboutBlinkActivity.this, VisitBlinkActivity.class);
        paramAnonymousView.putExtra(VisitBlinkFragment.URL_STRING, AboutBlinkActivity.this.getString(2131099963));
        paramAnonymousView.putExtra(VisitBlinkFragment.TITLE_STRING, AboutBlinkActivity.this.getString(2131099718));
        AboutBlinkActivity.this.startActivity(paramAnonymousView);
      }
    });
    this.mPrivacyPolicy = findViewById(2131558529);
    ((TextView)this.mPrivacyPolicy.findViewById(2131558530)).setText(2131099717);
    ((TextView)this.mPrivacyPolicy.findViewById(2131558531)).setText(">");
    this.mPrivacyPolicy.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        paramAnonymousView = new Intent(AboutBlinkActivity.this, VisitBlinkActivity.class);
        paramAnonymousView.putExtra(VisitBlinkFragment.URL_STRING, AboutBlinkActivity.this.getString(2131099917));
        paramAnonymousView.putExtra(VisitBlinkFragment.TITLE_STRING, AboutBlinkActivity.this.getString(2131099717));
        AboutBlinkActivity.this.startActivity(paramAnonymousView);
      }
    });
    try
    {
      paramBundle = getPackageManager().getPackageInfo(getPackageName(), 0);
      this.mVersion.setText("" + paramBundle.versionName);
      this.mBuildNumber.setText("" + paramBundle.versionCode);
      return;
    }
    catch (PackageManager.NameNotFoundException paramBundle)
    {
      paramBundle.printStackTrace();
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/activities/AboutBlinkActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */