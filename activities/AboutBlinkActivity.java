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
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          paramAnonymousView = new Intent(AboutBlinkActivity.this, VisitBlinkActivity.class);
          paramAnonymousView.putExtra(VisitBlinkFragment.URL_STRING, AboutBlinkActivity.this.getString(2131099966));
          paramAnonymousView.putExtra(VisitBlinkFragment.TITLE_STRING, AboutBlinkActivity.this.getString(2131099718));
          AboutBlinkActivity.this.startActivity(paramAnonymousView);
        }
      }
    });
    this.mPrivacyPolicy = findViewById(2131558529);
    ((TextView)this.mPrivacyPolicy.findViewById(2131558530)).setText(2131099717);
    ((TextView)this.mPrivacyPolicy.findViewById(2131558531)).setText(">");
    this.mPrivacyPolicy.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          paramAnonymousView = new Intent(AboutBlinkActivity.this, VisitBlinkActivity.class);
          paramAnonymousView.putExtra(VisitBlinkFragment.URL_STRING, AboutBlinkActivity.this.getString(2131099918));
          paramAnonymousView.putExtra(VisitBlinkFragment.TITLE_STRING, AboutBlinkActivity.this.getString(2131099717));
          AboutBlinkActivity.this.startActivity(paramAnonymousView);
        }
      }
    });
    try
    {
      paramBundle = getPackageManager().getPackageInfo(getPackageName(), 0);
      Object localObject2 = this.mVersion;
      Object localObject1 = new java/lang/StringBuilder;
      ((StringBuilder)localObject1).<init>();
      ((TextView)localObject2).setText("" + paramBundle.versionName);
      localObject1 = this.mBuildNumber;
      localObject2 = new java/lang/StringBuilder;
      ((StringBuilder)localObject2).<init>();
      ((TextView)localObject1).setText("" + paramBundle.versionCode);
      return;
    }
    catch (PackageManager.NameNotFoundException paramBundle)
    {
      for (;;)
      {
        paramBundle.printStackTrace();
      }
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/AboutBlinkActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */