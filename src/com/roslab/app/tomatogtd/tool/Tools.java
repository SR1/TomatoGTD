package com.roslab.app.tomatogtd.tool;

public class Tools {

	public static final String TAG = "Tools";

	// ��ʣ��ʱ��(����)ת��Ϊ mm:ss ��ʽ
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

	// ��ʣ��ʱ��(����)ת��Ϊ mm ��ʽ
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
