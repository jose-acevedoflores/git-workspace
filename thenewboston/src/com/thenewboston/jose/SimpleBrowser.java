package com.thenewboston.jose;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {
	
	private WebView ourBrow;
	private EditText url;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		
		ourBrow = (WebView) findViewById(R.id.wvBrowser);
		ourBrow.setWebViewClient(new OurViewClient());
		ourBrow.loadUrl("http://www.mybringback.com");
		
		Button go = (Button) findViewById(R.id.bGo);
		Button back = (Button) findViewById(R.id.bBack);
		Button refresh = (Button) findViewById(R.id.bRefresh);
		Button forward = (Button) findViewById(R.id.bForward);
		Button clearHistory = (Button) findViewById(R.id.bHistory); 
		
		url = (EditText) findViewById(R.id.etURL);
		
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);
		forward.setOnClickListener(this);
		clearHistory.setOnClickListener(this);
		
	}

	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.bGo:
			String webSite = url.getText().toString();
			ourBrow.loadUrl(webSite);
			break;
			
		case R.id.bBack:
			if(ourBrow.canGoBack())
				ourBrow.goBack();
			break;
			
		case R.id.bRefresh:
			ourBrow.reload();
			
			break;
			
		case R.id.bForward:
			if(ourBrow.canGoForward())
				ourBrow.goForward();
			
			break;
			
		case R.id.bHistory:
			ourBrow.clearHistory();
			break;
		}
		
	}

	private class OurViewClient extends WebViewClient{
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView v, String url)
		{
			v.loadUrl(url);
			return true;
		}
		
	}
	
}
