package com.cp.dma.settings;

import android.content.Context;

import com.cp.dma.data.Prefs;
import com.cp.dma.data.PrefsDataSource;

public class UserSettingsManagerDB {

	private PrefsDataSource prefsDatasource;
	private Prefs pref;

	private Prefs getPrefsDataSource(Context c) {
		if (prefsDatasource == null) {
			prefsDatasource = new PrefsDataSource(c);
		}
		prefsDatasource.open();
		if ((pref = prefsDatasource.getPref()) == null) {
			pref = prefsDatasource.createPref("melbourne", "metric", "white");
		}	
		prefsDatasource.close();
		return pref;
	}

}
