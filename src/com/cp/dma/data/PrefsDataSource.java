package com.cp.dma.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PrefsDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_CITY, 
			MySQLiteHelper.COLUMN_MEASUREMENT_TYPE,
			MySQLiteHelper.COLUMN_TEXTCOLOR};

	public PrefsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		Log.d(">>>DB OPEN>>", "opened");
	}

	public void close() {
		dbHelper.close();
	}

	public Prefs createPref(String city, String measurementType, String textColor) {

		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_CITY, city);
		values.put(MySQLiteHelper.COLUMN_MEASUREMENT_TYPE, measurementType);
		values.put(MySQLiteHelper.COLUMN_TEXTCOLOR, textColor);

		long insertId = database.insert(MySQLiteHelper.TABLE_PREFS, null, values);

		Log.d("CREATE PREF >>", Long.toString(insertId));

		Cursor cursor = database.query(MySQLiteHelper.TABLE_PREFS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);

		cursor.moveToFirst();
		Prefs newPref = cursorToPref(cursor);
		cursor.close();

		return newPref;
	}

	public Prefs updatePref(String city, String measurementType, String textColor) {

		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_CITY, city);
		values.put(MySQLiteHelper.COLUMN_MEASUREMENT_TYPE, measurementType);
		values.put(MySQLiteHelper.COLUMN_TEXTCOLOR, textColor);

		long numOfRows = database.update(MySQLiteHelper.TABLE_PREFS, values, "_id = 1", null);

		Log.d("UPDATE PREF rows affected >>", Long.toString(numOfRows));

		Cursor cursor = database.query(MySQLiteHelper.TABLE_PREFS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = 1", null,
				null, null, null);

		cursor.moveToFirst();
		Prefs newPref = cursorToPref(cursor);
		cursor.close();

		return newPref;
	}

	public void deletePref(Prefs pref) {

		long id = pref.getId();
		System.out.println("Pref deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_PREFS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public Prefs getPref() {

		Cursor cursor = database.query(MySQLiteHelper.TABLE_PREFS,
				allColumns, "_id = 1", null, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Prefs pref = cursorToPref(cursor);
			cursor.close();

			return pref;

		} else {

			return null;

		}


	}

	public List<Prefs> getAllPrefs() {

		List<Prefs> prefs = new ArrayList<Prefs>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PREFS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			Prefs pref = cursorToPref(cursor);
			prefs.add(pref);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();

		return prefs;
	}

	private Prefs cursorToPref(Cursor cursor) {

		Prefs pref = new Prefs();
		pref.setId(cursor.getLong(0));
		pref.setCityName(cursor.getString(1));
		pref.setMeasurementType(cursor.getString(2));
		pref.setTextColor(cursor.getString(3));

		return pref;
	}
}
