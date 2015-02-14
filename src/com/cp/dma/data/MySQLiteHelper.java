package com.cp.dma.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_PREFS = "prefs";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CITY = "city";
	public static final String COLUMN_MEASUREMENT_TYPE = "measurementtype";
	public static final String COLUMN_TEXTCOLOR = "textcolor";

	private static final String DATABASE_NAME = "UserSettings.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PREFS + "(" + 
			COLUMN_ID
			+ " integer primary key autoincrement, " + 
			COLUMN_CITY
			+ " text not null, " +
			COLUMN_MEASUREMENT_TYPE
			+ " text not null, " +
			COLUMN_TEXTCOLOR
			+ " text not null);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.d("DATABASE CREATE >>", DATABASE_CREATE);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREFS);
		onCreate(db);
	}

} 
