package biz.codefuture.svgviewer;

import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;
//import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {
	
//	private static final String TAG = "SVGViewerActivity";
	private boolean mIsFullscreen = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = getIntent();
	    Uri data = intent.getData();
	    //Log.v(TAG, "data=" + data);

	    if (data != null && data.toString().indexOf("file") > -1) {	
	    	WebView webview = (WebView) findViewById(R.id.webView1);
	    	webview.loadUrl(data.toString());
	    	webview.getSettings().setBuiltInZoomControls(true);
	    	webview.getSettings().setDisplayZoomControls(false);
	    	webview.getSettings().setUseWideViewPort(true);
	    	//webview.zoomOut();
	    }
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.menu_zoom_fit:
	        toggleFullscreen();
	        return true;
	    }
        Log.v("svg viewer", "selected menu item about to return super");
        return super.onOptionsItemSelected(item);
	}
	
	private void toggleFullscreen()
	{
	    Log.v("svg viewer", "about to toggle fullscreen");
		ActionBar actionBar = getActionBar();
		if (!mIsFullscreen) {
		  getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		  actionBar.hide();
	      Log.v("svg viewer", "set fullscreen");
	      mIsFullscreen = true;
	      Toast.makeText(getApplicationContext(), "Tap image to disable fullscreen", Toast.LENGTH_SHORT).show();
	    } else {
	      getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	      getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		  actionBar.show();
		  Log.v("svg viewer", "remove fullscreen");
	      mIsFullscreen = true;
	    }
	}
	
}
