package com.rtg.makovm;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MakoKeyboard extends LinearLayout {

	public interface MakoKeyboardListener
	{
		public void makoKeyboardKeyPressed(String key);
		public void makoKeyboardKeyReleased(String key);
		public void makoKeyboardKeyTyped(String key);
	}
	
	private MakoKeyboardListener mListener = null;
	
	private OnTouchListener mTouchListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(mListener==null)
			{
				return false;
			}
			
			String key = (String) v.getTag();
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				mListener.makoKeyboardKeyPressed(key);
			}
			else if(event.getAction() == MotionEvent.ACTION_UP)
			{
				mListener.makoKeyboardKeyReleased(key);
			}
			return false;
		}
	};
	
	public void setListener(MakoKeyboardListener mListener) {
		this.mListener = mListener;
	}

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
		
		// Tie up the callbacks on the keys
		for(int i = 0; i<getChildCount(); i++)
		{
			if(getChildAt(i) instanceof ViewGroup)
			{
				ViewGroup vg = (ViewGroup) getChildAt(i);
				for(int j = 0; j<vg.getChildCount(); j++)
				{
					if(vg.getChildAt(j).getTag()!=null)
					{
						vg.getChildAt(j).setOnTouchListener(mTouchListener);
					}
				}
			}
		}
	}
}
