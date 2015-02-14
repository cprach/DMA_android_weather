
package com.cp.dma.utility;

import com.example.dma1.R;

import android.app.Activity;
import android.content.Intent;


public class TextColorUtils {

	private static int cTheme;
	public final static int WHITE = 0;
	public final static int BLUE = 1;
	public final static int RED = 2;
	
	public static void setCTheme(int cThemeID) {
		cTheme = cThemeID;
	}
	
	public static void setCTheme(Activity activity, String textColor) {
		if (textColor.toLowerCase().equals("white")) {
			setCTheme(TextColorUtils.WHITE);
			changeToTheme(activity, TextColorUtils.WHITE);
		} else if (textColor.toLowerCase().equals("blue")) {
			setCTheme(TextColorUtils.BLUE);
			changeToTheme(activity, TextColorUtils.BLUE);
		} else if (textColor.toLowerCase().equals("red")) {
			setCTheme(TextColorUtils.RED);
			changeToTheme(activity, TextColorUtils.RED);
		}
	}

	public static String getCurrentThemeName() {
		switch (cTheme) {
		case WHITE:
			return "white";
		case BLUE:
			return "blue";
		case RED:
			return "red";
		default:
			return "white";
		}
	}

	public static void changeToTheme(Activity activity, int theme) {

		cTheme = theme;
		activity.finish();
		Intent i = new Intent(activity, activity.getClass());
		i.putExtra("firstLoad", "firstLoad");
		activity.startActivity(i);

	}

	public static void onActivityCreateSetTheme(Activity activity) {

		switch (cTheme) {

		case WHITE:
			activity.setTheme(R.style.WhiteTheme);
			break;

		case BLUE:
			activity.setTheme(R.style.BlueTheme);
			break;

		case RED:
			activity.setTheme(R.style.RedTheme);
			break;

		default:
			activity.setTheme(R.style.WhiteTheme);

		}
		
		
//		switch (cTheme) {
//
//		case WHITE:
//			activity.setTheme(R.style.WhiteTheme);
//			break;
//
//		case BLUE:
//			activity.setTheme(R.style.BlueTheme);
//			break;
//
//		case RED:
//			activity.setTheme(R.style.RedTheme);
//			break;
//
//		default:
//			activity.setTheme(R.style.WhiteTheme);
//
//		}

	}
	
//	public static void onActivityCreateSetTheme(Activity activity) {
//
//		switch (cTheme) {
//
//		case WHITE:
//			activity.setTheme(R.style.WhiteTheme);
//			break;
//
//		case BLUE:
//			activity.setTheme(R.style.BlueTheme);
//			break;
//
//		case RED:
//			activity.setTheme(R.style.RedTheme);
//			break;
//
//		default:
//			activity.setTheme(R.style.WhiteTheme);
//
//		}
//
//	}

}



//package com.cp.dma.utility;
//
//import com.example.dma1.R;
//
//import android.app.Activity;
//import android.content.Intent;
//
//
//public class TextColorUtils {
//
//	private static int cTheme;
//	public final static int WHITE = 0;
//	public final static int BLUE = 1;
//	public final static int RED = 2;
//	
//	public static void setCTheme(int cThemeID) {
//		cTheme = cThemeID;
//	}
//	
//	public static void setCTheme(Activity activity, String textColor) {
//		if (textColor.toLowerCase().equals("white")) {
//			changeToTheme(activity, TextColorUtils.WHITE);
//			setCTheme(TextColorUtils.WHITE);
//		} else if (textColor.toLowerCase().equals("blue")) {
//			changeToTheme(activity, TextColorUtils.BLUE);
//			setCTheme(TextColorUtils.BLUE);
//		} else if (textColor.toLowerCase().equals("red")) {
//			changeToTheme(activity, TextColorUtils.RED);
//			setCTheme(TextColorUtils.RED);
//		}
//	}
//
//	public static String getCurrentThemeName() {
//		switch (cTheme) {
//		case WHITE:
//			return "white";
//		case BLUE:
//			return "blue";
//		case RED:
//			return "red";
//		default:
//			return "white";
//		}
//	}
//
//	public static void changeToTheme(Activity activity, int theme) {
//
//		cTheme = theme;
//		activity.finish();
//		Intent i = new Intent(activity, activity.getClass());
//		i.putExtra("firstLoad", "firstLoad");
//		activity.startActivity(i);
//
//	}
//
//	public static void onActivityCreateSetTheme(Activity activity) {
//
//		switch (cTheme) {
//
//		case WHITE:
//			activity.setTheme(R.style.WhiteTheme);
//			break;
//
//		case BLUE:
//			activity.setTheme(R.style.BlueTheme);
//			break;
//
//		case RED:
//			activity.setTheme(R.style.RedTheme);
//			break;
//
//		default:
//			activity.setTheme(R.style.WhiteTheme);
//
//		}
//		
//		
////		switch (cTheme) {
////
////		case WHITE:
////			activity.setTheme(R.style.WhiteTheme);
////			break;
////
////		case BLUE:
////			activity.setTheme(R.style.BlueTheme);
////			break;
////
////		case RED:
////			activity.setTheme(R.style.RedTheme);
////			break;
////
////		default:
////			activity.setTheme(R.style.WhiteTheme);
////
////		}
//
//	}
//	
////	public static void onActivityCreateSetTheme(Activity activity) {
////
////		switch (cTheme) {
////
////		case WHITE:
////			activity.setTheme(R.style.WhiteTheme);
////			break;
////
////		case BLUE:
////			activity.setTheme(R.style.BlueTheme);
////			break;
////
////		case RED:
////			activity.setTheme(R.style.RedTheme);
////			break;
////
////		default:
////			activity.setTheme(R.style.WhiteTheme);
////
////		}
////
////	}
//
//}
