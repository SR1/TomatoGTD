package com.roslab.app.tomatogtd.tool;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;

import com.roslab.app.tomatogtd.R;


public class RandomColorId {
	
	public static String TAG = "RandomColorId";
	
	public static Integer seed;
	public static ArrayList<Integer> colorList;
	public static int size;
	
	public static void initColorList()
	{
		colorList = new ArrayList<Integer>();

		colorList.add(R.color.bgcolor_blue);
		colorList.add(R.color.bgcolor_yellow);
		colorList.add(R.color.bgcolor_pink);
		colorList.add(R.color.bgcolor_green);
		
		size = colorList.size();
	}
	
	public static int getColorId(int position)
	{
		Random ran = new Random();
		if (seed == null)
			seed = ran.nextInt(1000);
		initColorList();
		
		Log.v(TAG,"on getColorId--->seed"+seed);
		return colorList.get((seed+position)%size);
	}
}
