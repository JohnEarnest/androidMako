package com.rtg.makovm;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	private MakoView view = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Retrieve a reference to the view inflated in the layout
		view = (MakoView) findViewById(R.id.MainActivity_MakoView);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		view.onKeyUp(keyCode, event);
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		view.onKeyDown(keyCode, event);
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		view.dispatchKeyEvent(event);
		return super.dispatchKeyEvent(event);
	}

	@Override
	public void onBackPressed() {
		return;
	}
}
