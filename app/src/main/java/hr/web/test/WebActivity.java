package hr.web.test;

import hr.web.test.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class WebActivity extends Activity {
    /** Called when the activity is first created. */
	WebView myWebView =null;
	Button button =null;
	Handler mHandler=null;
	boolean ischange=false;

	@SuppressLint({"setJavaScriptEnabled","addJavascriptInterface"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //button= (Button)this.findViewById(R.id.button1);
        
        //web view work
        myWebView = (WebView)findViewById(R.id.webView1);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        //myWebView.addJavascriptInterface(this, "Android");
        

        
        
        Log.d("Web Activity", "on create");
        mHandler = new Handler();
    }
	 public void showToast(String toast) {
	        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
	        this.button.setText("very new changed");
	    }
	public void clicked(View V)
	{
		if(!ischange)
		{
			button.setText("Change");
			ischange=true;
		}
		else
		{
			button.setText("Button");
			ischange=false;
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("Web Activity", "destroid");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("Web Activity", "restart");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("Web Activity", "paused");
	}
	@Override
	protected void onResume() {
		super.onResume();
        //myWebView.getSettings().setLoadWithOverviewMode(true);
        //myWebView.getSettings().setUseWideViewPort(true);
		Log.d("Web Activity", "resume");
		myWebView.loadUrl("file:///android_asset/index.html");
		
	}
    
}