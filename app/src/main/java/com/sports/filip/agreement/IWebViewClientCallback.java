package com.sports.filip.agreement;

import android.webkit.WebView;


/**
 * @author fdrs 
 * @date 20150721
 * */
public interface IWebViewClientCallback {
	public void onReceivedError(WebView view, int errorCode,
								String description, String failingUrl);

	public void onPageFinished(WebView view, String url);

	public void onPageStarted(WebView view, String url,
							  android.graphics.Bitmap favicon);

	public void onLoadResource(WebView view, String url);
	
	public void shouldOverrideUrlLoading(WebView view, String url);
}
