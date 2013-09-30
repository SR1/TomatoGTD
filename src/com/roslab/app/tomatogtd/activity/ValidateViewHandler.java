package com.roslab.app.tomatogtd.activity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ValidateViewHandler extends Handler {
	
	public static final String TAG = "ValidateViewHandler";

	public static final int UPDATE_LOOP = 1;
	public static final int UPDATE_NOW = 2;
	
	private OnValidateListener listener;
	
	public ValidateViewHandler(OnValidateListener listener){
		this.listener = listener;
	}
	
	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case UPDATE_LOOP:
			if(listener!=null)
				listener.onValidate();
			sendEmptyMessageDelayed(UPDATE_LOOP, 500);
			break;

		case UPDATE_NOW:
			if(listener!=null)
				listener.onValidate();
			break;
		}
		Log.v(TAG, "handleMessage-->"+msg.what);
	}
	
	public interface OnValidateListener{
		/***
		 * Ñ­»·¸üĞÂ
		 */
		public void onValidate();
	}
}
