package com.rtg.makovm;

import java.util.*;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MakoKeyboard extends LinearLayout {

	public interface MakoKeyboardListener {
		public void makoKeyboardKeyPressed(String key);
		public void makoKeyboardKeyReleased(String key);
		public void makoKeyboardKeyTyped(String key);
	}

	private MakoKeyboardListener mListener = null;
	private Button               mShift    = null;
	private boolean              mShifted  = false;
	private Map<Button, Key>     keys      = new HashMap<Button, Key>();

	private OnClickListener mShiftClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			setShifted(!mShifted);
		}
	};
	
	private OnTouchListener mTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(mListener==null) {
				return false;
			}

			if(event.getAction() == MotionEvent.ACTION_DOWN) {
				if (keys.containsKey(v)) {
					Key k = keys.get((Button)v);
					mListener.makoKeyboardKeyPressed(mShifted ? k.shifted : k.normal);
				}
				else {
					mListener.makoKeyboardKeyPressed((String)v.getTag());
				}
			}
			else if(event.getAction() == MotionEvent.ACTION_UP) {
				if (keys.containsKey(v)) {
					Key k = keys.get((Button)v);
					mListener.makoKeyboardKeyReleased(mShifted ? k.shifted : k.normal);
				}
				else {
					mListener.makoKeyboardKeyReleased((String)v.getTag());
				}
				setShifted(false);
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

	private void mapKey(String tag, String shifted) {
		Button b = (Button)findViewWithTag(tag);
		keys.put(b, new Key(tag, shifted));
		b.setText(tag);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		mapKey("1", "!");
		mapKey("2", "@");
		mapKey("3", "#");
		mapKey("4", "$");
		mapKey("5", "%");
		mapKey("6", "^");
		mapKey("7", "&");
		mapKey("8", "*");
		mapKey("9", "(");
		mapKey("0", ")");
		mapKey("-", "_");
		mapKey("=", "+");

		mapKey("q", "Q");
		mapKey("w", "W");
		mapKey("e", "E");
		mapKey("r", "R");
		mapKey("t", "T");
		mapKey("y", "Y");
		mapKey("u", "U");
		mapKey("i", "I");
		mapKey("o", "O");
		mapKey("p", "P");
		mapKey("[", "{");
		mapKey("]", "}");

		mapKey("a", "A");
		mapKey("s", "S");
		mapKey("d", "D");
		mapKey("f", "F");
		mapKey("g", "G");
		mapKey("h", "H");
		mapKey("j", "J");
		mapKey("k", "K");
		mapKey("l", "L");
		mapKey(":", ";");
		mapKey("'", "\"");

		mapKey("z", "Z");
		mapKey("x", "X");
		mapKey("c", "C");
		mapKey("v", "V");
		mapKey("b", "B");
		mapKey("n", "N");
		mapKey("m", "M");
		mapKey(",", "<");
		mapKey(".", ">");
		mapKey("?", "/");

		mapKey("Del", "Brk");

		mShift = (Button)findViewById(R.id.MakoKeyboard_Shift);
		mShift.setOnClickListener(mShiftClickListener);
		
		// Tie up the callbacks on the keys
		for(int i = 0; i<getChildCount(); i++) {
			if(getChildAt(i) instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) getChildAt(i);
				for(int j = 0; j<vg.getChildCount(); j++) {
					if(vg.getChildAt(j).getTag()!=null) {
						vg.getChildAt(j).setOnTouchListener(mTouchListener);
					}
				}
			}
		}
	}

	private void setShifted(boolean shifted) {
		mShifted = shifted;
		mShift.setActivated(shifted);

		for(Map.Entry<Button, Key> e : keys.entrySet()) {
			e.getKey().setText(shifted? e.getValue().shifted : e.getValue().normal);
		}
	}
}

class Key {
	String normal;
	String shifted;

	Key(String normal, String shifted) {
		this.normal  = normal;
		this.shifted = shifted;
	}
}