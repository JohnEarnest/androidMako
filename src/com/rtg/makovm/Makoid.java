package com.rtg.makovm;

import android.util.Log;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class Makoid extends Activity {
	private MakoView view = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Retrieve a reference to the view inflated in the layout
		view = (MakoView) findViewById(R.id.Makoid_MakoView);
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

	public void softKey(View v) {
		if (v.getTag() == null) { return; }
		String tag = (String)v.getTag();

		if      ("Delete".equals(tag)) { view.keyTyped( 8); }
		else if ( "Enter".equals(tag)) { view.keyTyped(10); }
		else if (    "up".equals(tag)) { view.setKeys(MakoConstants.KEY_UP); }
		else if (    "dn".equals(tag)) { view.setKeys(MakoConstants.KEY_DN); }
		else if (    "lf".equals(tag)) { view.setKeys(MakoConstants.KEY_LF); }
		else if (    "rt".equals(tag)) { view.setKeys(MakoConstants.KEY_RT); }
		else {
			view.keyTyped((int)tag.charAt(0));
		}
	}
}
