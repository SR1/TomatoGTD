package com.roslab.app.tomatogtd.tool;

public class Tools {

	public static final String TAG = "Tools";

	// 将剩余时间(毫秒)转换为 mm:ss 格式
	public static String TransToString(long time) {
		StringBuilder s = new StringBuilder();

		int t;
		t = (int) time / 1000 / 60;
		if (t < 10)
			s.append(0);
		s.append(t + ":");
		t = (int) time / 1000 % 60;
		if (t < 10)
			s.append(0);
		s.append(t);

		return s.toString();
	}

	// 将剩余时间(毫秒)转换为 mm 格式
	public static String TransToMinute(long time) {
		StringBuilder s = new StringBuilder();

		int t;
		t = (int) time / 1000 / 60;
		if (t < 10)
			s.append(0);
		s.append(t);

		return s.toString();
	}
}
