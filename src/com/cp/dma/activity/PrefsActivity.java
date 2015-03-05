package com.cp.dma.activity;

// Instructor@computerpower

import com.example.dma1.R;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PrefsActivity extends ActionBarActivity implements OnItemSelectedListener {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// View customizations must occur before the View is set.		
		//customiseView();
		setContentView(R.layout.activity_prefs);
		intitialiseSpinners();
		populateLabels();
	}

	private void customiseView() {
		if (getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_TEXTCOLOR, PREFKEYCHECK).equals(TEXT_WHITE)) {
			setTheme(R.style.WhiteTheme);
		} else if (getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_TEXTCOLOR, PREFKEYCHECK).equals(TEXT_BLUE)) {
			setTheme(R.style.BlueTheme);
		} else if (getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_TEXTCOLOR, PREFKEYCHECK).equals(TEXT_RED)) {
			setTheme(R.style.RedTheme);
		} else {
			setTheme(R.style.WhiteTheme);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.action_about:
			launchAboutActivity();
			break;
		case R.id.action_getWeather:
			launchGetWeatherActivity();
			break;
		default:
			return false;
		}
		return super.onOptionsItemSelected(item);
	}

	private void launchAboutActivity(){
		Intent i = new Intent(this,AboutActivity.class);
		startActivity(i);
	}

	private void launchGetWeatherActivity(){
		Intent i = new Intent(this,GetWeatherActivity.class);
		startActivity(i);
	}

	private void intitialiseSpinners() {
		Spinner cities_spinner = (Spinner)findViewById(R.id.cities_spinner);
		cities_spinner.setOnItemSelectedListener(this);
		ArrayAdapter <CharSequence> citiesAdapter = ArrayAdapter.createFromResource(this, 
				R.array.cities_array, android.R.layout.simple_spinner_item);
		citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cities_spinner.setAdapter(citiesAdapter);

		Spinner units_spinner = (Spinner)findViewById(R.id.units_spinner);
		units_spinner.setOnItemSelectedListener(this);
		ArrayAdapter <CharSequence> unitsAdapter = ArrayAdapter.createFromResource(this, 
				R.array.units_array, android.R.layout.simple_spinner_item);
		unitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		units_spinner.setAdapter(unitsAdapter);

		Spinner textcolor_spinner = (Spinner)findViewById(R.id.textcolor_spinner);
		textcolor_spinner.setOnItemSelectedListener(this);
		ArrayAdapter <CharSequence> textcolorAdapter = ArrayAdapter.createFromResource(this, 
				R.array.textcolor_array, android.R.layout.simple_spinner_item);
		textcolorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		textcolor_spinner.setAdapter(textcolorAdapter);
	}

	private void populateLabels() {  
		TextView txtCurrentCityName = (TextView)findViewById(R.id.txtCurrentCityName);
		TextView txtCurrentMeasurementTypeName = (TextView)findViewById(R.id.txtCurrentMeasurementTypeName);
		TextView txtCurrentTextColorName = (TextView)findViewById(R.id.txtCurrentTextColorName);

		txtCurrentCityName.setText(getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE));
		txtCurrentMeasurementTypeName.setText(getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_MEASUREMENTTYPE, DEFAULT_MEASUREMENTTYPE_METRIC));
		txtCurrentTextColorName.setText(getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_TEXTCOLOR, TEXT_WHITE));
	}
	
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		if (pos > 0) { // If a selection has been made.
			TextView txtCurrentCityName = (TextView)findViewById(R.id.txtCurrentCityName);
			TextView txtCurrentMeasurementTypeName = (TextView)findViewById(R.id.txtCurrentMeasurementTypeName);
			TextView txtCurrentTextColorName = (TextView)findViewById(R.id.txtCurrentTextColorName);

			String selection = (String)parent.getItemAtPosition(pos);
			//Spinner spinner = (Spinner)parent;

			if (parent.getId() == R.id.cities_spinner) {
				txtCurrentCityName.setText(selection);
			}
			if (parent.getId() == R.id.units_spinner) {
				txtCurrentMeasurementTypeName.setText(selection);
			}
			if (parent.getId() == R.id.textcolor_spinner) {
				txtCurrentTextColorName.setText(selection);
			}
		}
	}

//	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//		if (pos > 0) { // If a selection has been made.
//			TextView txtCurrentCityName = (TextView)findViewById(R.id.txtCurrentCityName);
//			TextView txtCurrentMeasurementTypeName = (TextView)findViewById(R.id.txtCurrentMeasurementTypeName);
//			TextView txtCurrentTextColorName = (TextView)findViewById(R.id.txtCurrentTextColorName);
//
//			String selection = (String)parent.getItemAtPosition(pos);
//			Spinner spinner = (Spinner)parent;
//
//			if (spinner.getId() == R.id.cities_spinner) {
//				txtCurrentCityName.setText(selection);
//			}
//			if (spinner.getId() == R.id.units_spinner) {
//				txtCurrentMeasurementTypeName.setText(selection);
//			}
//			if (spinner.getId() == R.id.textcolor_spinner) {
//				txtCurrentTextColorName.setText(selection);
//			}
//		}
//	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		return;
	}

	public void savePref(View view) {
		TextView txtCurrentCityName = (TextView)findViewById(R.id.txtCurrentCityName);
		TextView txtCurrentMeasurementTypeName = (TextView)findViewById(R.id.txtCurrentMeasurementTypeName);
		TextView txtCurrentTextColorName = (TextView)findViewById(R.id.txtCurrentTextColorName);

		Editor e = getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).edit();
		e.putString(PREFKEY_CITY, txtCurrentCityName.getText().toString());
		e.putString(PREFKEY_MEASUREMENTTYPE, txtCurrentMeasurementTypeName.getText().toString());
		e.putString(PREFKEY_TEXTCOLOR, txtCurrentTextColorName.getText().toString());
		e.commit();

		Toast.makeText(this, "Your preferences have been updated to " + txtCurrentCityName.getText() 
				+ ", " + txtCurrentMeasurementTypeName.getText() + ", " 
				+ txtCurrentTextColorName.getText(), Toast.LENGTH_SHORT).show();

		finish();
		Intent i = new Intent(this, this.getClass());
		this.startActivity(i);
	}

	public void closePrefs(View view) {
		// Return to weather activity.
		Intent i = new Intent(this, GetWeatherActivity.class);
		startActivity(i);
	}

	private void log(String msg) {
		Log.d(this.getClass().getSimpleName(), ">>> " + msg);
	}



}
