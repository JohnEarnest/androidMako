package com.rtg.makovm;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MakoKeyboard extends LinearLayout {

	public MakoKeyboard(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MakoKeyboard(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MakoKeyboard(Context context) {
		this(context, null, 0);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
	}
}
