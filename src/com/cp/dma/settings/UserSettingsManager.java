package com.cp.dma.settings;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class UserSettingsManager {

	private static SharedPreferences userPreferences;

	private final static String PREFSNAME = "dmaprefs";
	private final static int PREF_MODE_PRIVATE = 0;
	private final static String PREFKEY_TEXTCOLOR = "textcolor";
	private final static String PREFKEY_CITY = "city";
	private final static String PREFKEY_MEASUREMENTTYPE = "meaurementtype";
	private final static String PREFKEYCHECK = "empty";
	
	private final static String TEXT_WHITE = "White";
	private final static String TEXT_BLUE = "Blue";
	private final static String TEXT_RED = "Red";
	
	private final static String DEFAULT_CITY_MELBOURNE = "Melbourne";
	private final static String DEFAULT_MEASUREMENTTYPE_METRIC = "Metric";
	
	public UserSettingsManager(Activity a) {
		initialiseUserPreferences(a);
	}

	public static void initialiseUserPreferences(Activity a) {
		log("initialiseUserPreferences");
		if(userPreferences == null) {
			log("initialiseUserPreferences - userPreferences is NULL");
			userPreferences = a.getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE);
			log(("initialiseUserPreferences - pref text color = " + userPreferences.getString(PREFKEY_TEXTCOLOR, PREFKEYCHECK)));

//			Editor e = userPreferences.edit();
//			e.putString(PREFKEY_TEXTCOLOR, TEXT_WHITE);
//			e.putString(PREFKEY_MEASUREMENTTYPE, DEFAULT_MEASUREMENTTYPE_METRIC);
//			e.putString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE);
//			e.commit();
		}
		if ((userPreferences.getString(PREFKEY_TEXTCOLOR, PREFKEYCHECK)).equals(PREFKEYCHECK)) {
			log("TEXT COLOR EMPTY RECREATING ALL");
			Editor e = userPreferences.edit();
			e.putString(PREFKEY_TEXTCOLOR, TEXT_WHITE);
			e.putString(PREFKEY_MEASUREMENTTYPE, DEFAULT_MEASUREMENTTYPE_METRIC);
			e.putString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE);
			e.commit();
		}
		// Check if the prefs have previously been set, if not set defaults.
		// (Assumes that if text color has not been set then nothing has been set.
//		if ((userPreferences.getString(PREFKEY_TEXTCOLOR, PREFKEYCHECK)).equals(PREFKEYCHECK)) {
//			log("TEXT COLOR EMPTY RECREATING ALL");
//			Editor e = userPreferences.edit();
//			e.putString(PREFKEY_TEXTCOLOR, TEXT_WHITE);
//			e.putString(PREFKEY_MEASUREMENTTYPE, DEFAULT_MEASUREMENTTYPE_METRIC);
//			e.putString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE);
//			e.commit();
//		}
	}

	public static String getUserPreference(Activity a, String prefKeyName) {
		log("getUserPreference");
		log("getUserPreference - " + prefKeyName);

		//initialiseUserPreferences(a);
		//userPreferences = a.getPreferences(PREF_MODE_PRIVATE);
		String prefValue;
		// Check if the prefs have previously been set, if not set defaults.
		// (Assumes that if text color has not been set then nothing has been set.
//		if (userPreferences.getString(PREFKEY_TEXTCOLOR, PREFKEYCHECK).equals(PREFKEYCHECK)) {
//			Editor e = userPreferences.edit();
//			e.putString(PREFKEY_TEXTCOLOR, TEXT_WHITE);
//			e.putString(PREFKEY_MEASUREMENTTYPE, DEFAULT_MEASUREMENTTYPE_METRIC);
//			e.putString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE);
//			e.commit();
//		} 
		prefValue = userPreferences.getString(prefKeyName, PREFKEYCHECK);
		return prefValue;
	}
	
	public static boolean setUserPreference(Activity a, String prefKeyName, String prefKeyValue) {
		log("setUserPreference");
		log("setUserPreference - " + prefKeyName + " = " + prefKeyValue);

		//initialiseUserPreferences(a);
		//userPreferences = a.getPreferences(PREF_MODE_PRIVATE);
		// Check if the prefs have previously been set, if not set defaults.
		// (Assumes that if text color has not been set then nothing has been set.
		Editor e = userPreferences.edit();
//		if (userPreferences.getString(PREFKEY_TEXTCOLOR, PREFKEYCHECK).equals(PREFKEYCHECK)) {
//			e.putString(PREFKEY_TEXTCOLOR, TEXT_WHITE);
//			e.putString(PREFKEY_MEASUREMENTTYPE, DEFAULT_MEASUREMENTTYPE_METRIC);
//			e.putString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE);
//		} 
		e.putString(prefKeyName, prefKeyValue);
		return e.commit();
	}
	
	private static void log(String msg) {
		//Log.d("UserSettingsManager, ">>> " + msg);
	}


//	public void initialiseUserPreferences(Activity a) {
//		userPreferences = a.getPreferences(PREF_MODE_PRIVATE);
//		// Check if the prefs have previously been set, if not set defaults.
//		// (Assumes that if text color has not been set then nothing has been set.
//		if (userPreferences.getString(PREFKEY_TEXTCOLOR, PREFKEYCHECK).equals(PREFKEYCHECK)) {
//			Editor e = userPreferences.edit();
//			e.putString(PREFKEY_TEXTCOLOR, TEXT_WHITE);
//			e.putString(PREFKEY_MEASUREMENTTYPE, DEFAULT_MEASUREMENTTYPE_METRIC);
//			e.putString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE);
//			e.commit();
//		}
//	}
//
//	public String getUserPreference(Activity a, String prefKeyName) {
//		initialiseUserPreferences(a);
//		//userPreferences = a.getPreferences(PREF_MODE_PRIVATE);
//		String prefValue;
//		// Check if the prefs have previously been set, if not set defaults.
//		// (Assumes that if text color has not been set then nothing has been set.
////		if (userPreferences.getString(PREFKEY_TEXTCOLOR, PREFKEYCHECK).equals(PREFKEYCHECK)) {
////			Editor e = userPreferences.edit();
////			e.putString(PREFKEY_TEXTCOLOR, TEXT_WHITE);
////			e.putString(PREFKEY_MEASUREMENTTYPE, DEFAULT_MEASUREMENTTYPE_METRIC);
////			e.putString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE);
////			e.commit();
////		} 
//		prefValue = userPreferences.getString(prefKeyName, PREFKEYCHECK);
//		return prefValue;
//	}
//	
//	public boolean setUserPreference(Activity a, String prefKeyName, String prefKeyValue) {
//		initialiseUserPreferences(a);
//		//userPreferences = a.getPreferences(PREF_MODE_PRIVATE);
//		// Check if the prefs have previously been set, if not set defaults.
//		// (Assumes that if text color has not been set then nothing has been set.
//		Editor e = userPreferences.edit();
////		if (userPreferences.getString(PREFKEY_TEXTCOLOR, PREFKEYCHECK).equals(PREFKEYCHECK)) {
////			e.putString(PREFKEY_TEXTCOLOR, TEXT_WHITE);
////			e.putString(PREFKEY_MEASUREMENTTYPE, DEFAULT_MEASUREMENTTYPE_METRIC);
////			e.putString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE);
////		} 
//		e.putString(prefKeyName, prefKeyValue.toLowerCase(Locale.getDefault()));
//		return e.commit();
//	}



}
