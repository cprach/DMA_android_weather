package com.cp.dma.activity;

//Instructor@computerpower

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.cp.dma.javaopenweatherobjects.List;
import com.cp.dma.javaopenweatherobjects.Weather;
import com.cp.dma.utility.ConvertTime;
import com.example.dma1.R;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class GetWeatherActivity extends ActionBarActivity {

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
		customiseView();
		setContentView(R.layout.activity_getweather);
		// Set the labels.
		setCityNameText();
		setCurrentTimeText();
		showPreferencesToast();
	}

	@Override
	protected void onStart() {
		super.onStart();		
		// Get the weather.
		// Starting here so we can take advantage of the progress bar feedback.
		getWeather(getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE),
				getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_MEASUREMENTTYPE, DEFAULT_MEASUREMENTTYPE_METRIC));
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

	private void showPreferencesToast() {
		Toast.makeText(this, getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE) 
				+ " has been set as the default city. This can be changed via your preferences", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items from menu/menu.xml.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.action_settings:
			launchPreferencesActivity();
			break;
		case R.id.action_about:
			launchAboutActivity();
			break;
		default:
			return false;
		}
		return super.onOptionsItemSelected(item);
	}

	public void launchPreferencesActivity(View v){
		launchPreferencesActivity();
		finish();
	}

	private void launchPreferencesActivity(){
		Intent i = new Intent(this,PrefsActivity.class);
		startActivity(i);
	}

	private void launchAboutActivity(){
		Intent i = new Intent(this,AboutActivity.class);
		startActivity(i);
	}

	private void setCityNameText() {
		TextView txtCityName = (TextView)findViewById(R.id.txtCityName);		
		txtCityName.setText(getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE));
	}

	private void setCurrentTimeText() {
		TextView txtTime = (TextView)findViewById(R.id.txtTime);
		txtTime.setText(ConvertTime.getCurrentTime());
	}

	public void getWeather(String cityName, String measurementType) {
		final ProgressBar progressBar = (ProgressBar) this.findViewById(R.id.prog);
		progressBar.setVisibility(View.VISIBLE);

		// Pass the progress bar to the async task.
		getInfo gi = new getInfo(progressBar);
		// Pass city name and measurement type to execute request.
		gi.execute(cityName, measurementType);
	}

	public void convertJsonString(String result) {

		// Called by asyncTask onPostExecute(...
		// Deserialises JSON.

		// Using Google's Gson library.
		Gson gson = new Gson();
		// Deserialised into RootObject class.
		com.cp.dma.javaopenweatherobjects.RootObject ro = gson.fromJson(result, com.cp.dma.javaopenweatherobjects.RootObject.class);

		// Array of TextViews in the layout, for displaying the forecast time.
		TextView textViews[] = new TextView[8];
		textViews[0] = (TextView)findViewById(R.id.textViewimg1);
		textViews[1] = (TextView)findViewById(R.id.textViewimg2);
		textViews[2] = (TextView)findViewById(R.id.textViewimg3);
		textViews[3] = (TextView)findViewById(R.id.textViewimg4);
		textViews[4] = (TextView)findViewById(R.id.textViewimg5);
		textViews[5] = (TextView)findViewById(R.id.textViewimg6);
		textViews[6] = (TextView)findViewById(R.id.textViewimg7);
		textViews[7] = (TextView)findViewById(R.id.textViewimg8);

		// Array of ImageViews in the layout, for displaying the forecast icon.
		ImageView imageViews[] = new ImageView[8];
		imageViews[0] = (ImageView)findViewById(R.id.imgweather1);
		imageViews[1] = (ImageView)findViewById(R.id.imgweather2);
		imageViews[2] = (ImageView)findViewById(R.id.imgweather3);
		imageViews[3] = (ImageView)findViewById(R.id.imgweather4);
		imageViews[4] = (ImageView)findViewById(R.id.imgweather5);
		imageViews[5] = (ImageView)findViewById(R.id.imgweather6);
		imageViews[6] = (ImageView)findViewById(R.id.imgweather7);
		imageViews[7] = (ImageView)findViewById(R.id.imgweather8);

		// Min and max temps in the 24 hours forecast.
		double minTemp = 0;
		double maxTemp = 0;

		// Loop through the first 8 (24 hours) List objects.
		for (int viewCounter = 0; viewCounter < 8; viewCounter++) {

			List currentList = ro.getList().get(viewCounter);

			// min and max temps.
			if (viewCounter == 0) { // if first pass.
				minTemp = currentList.getMain().getTempMin();
				maxTemp = currentList.getMain().getTempMax();
			} else { // if subsequent pass.
				if (currentList.getMain().getTempMin() < minTemp) {
					minTemp = currentList.getMain().getTempMin();
				}
				if (currentList.getMain().getTempMax() > maxTemp) {
					maxTemp = currentList.getMain().getTempMax();
				}
			}

			// Get the Weather object from the current List object.
			Weather wweather = currentList.getWeather().get(0);

			// Get icon name, get its resource id and set the icon in the current imageview.
			String jsonIconName = wweather.getIcon();
			int iconResourceName = getResources().getIdentifier("img" + jsonIconName, "drawable", getPackageName());
			imageViews[viewCounter].setImageResource(iconResourceName); 

			// Get the datetime, convert it to a time string and set the time in the current textview.
			String dateTime = ConvertTime.makeTimeString(currentList.getDtTxt(), getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE));
			textViews[viewCounter].setText(dateTime);
		}

		// Get the first datetime field in the list (current date).
		String jsonDateTimeField = ro.getList().get(0).getDtTxt();
		// Convert the datetime to a display date and set it in the textview.
		String displayDate = ConvertTime.makeDateString(jsonDateTimeField, getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_CITY, DEFAULT_CITY_MELBOURNE));
		TextView txtDate = (TextView)findViewById(R.id.txtDate);
		txtDate.setText(displayDate);

		// Get the current temp from the first weather in the list and set it in the textview.
		String currentTemp = String.format("%.1f",ro.getList().get(0).getMain().getTemp()); //
		TextView txtCurrentTempValue = (TextView)findViewById(R.id.txtCurrentTempValue); //
		txtCurrentTempValue.setText(currentTemp); //

		// Set measurement type display
		TextView txtMeasurementType = (TextView)findViewById(R.id.txtMeasurementType);
		String storedMeasurementType = getSharedPreferences(PREFSNAME,PREF_MODE_PRIVATE).getString(PREFKEY_MEASUREMENTTYPE, DEFAULT_MEASUREMENTTYPE_METRIC);
		String measurementType = "empty";
		if (storedMeasurementType.equals("Metric")) {
			measurementType = "Celcius";
		} else {
			measurementType = "Farenheight";
		}
		txtMeasurementType.setText(measurementType);
		
		// Set the min and max temps in the textviews.
		TextView txtMinValue = (TextView)findViewById(R.id.txtMinValue);
		txtMinValue.setText(String.format("%.1f", minTemp));
		TextView txtMaxValue = (TextView)findViewById(R.id.txtMaxValue);
		txtMaxValue.setText(String.format("%.1f", maxTemp));

	}


	// INLINE CLASS

	private class getInfo extends AsyncTask<String, Integer, String> {

		final static String SERVICE_ADDRESS = "http://api.openweathermap.org/data/2.5/forecast?";
		final static String CITY_KEY_TEXT = "q=";
		final static String MEASUREMENTTYPE_KEY_TEXT = "&units=";

		ProgressBar progressBar;

		public getInfo(ProgressBar progressBar) {
			this.progressBar = progressBar;
			progressBar.setProgress(0);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			if (this.progressBar != null) {
				progressBar.setProgress(values[0]);
			}
		}

		@Override
		protected String doInBackground(String... urls) {
			String address = SERVICE_ADDRESS + CITY_KEY_TEXT + urls[0] + ",au" + MEASUREMENTTYPE_KEY_TEXT + urls[1];		
			StringBuilder response = new StringBuilder();
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(address);
			try {
				HttpResponse execute = client.execute(httpGet);
				InputStream content = execute.getEntity().getContent();

				BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
				String bufferString = "";
				while ((bufferString = buffer.readLine()) != null) {
					response.append(bufferString);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return response.toString();
		}

		@Override
		protected void onPostExecute(String result) {

			if (result == "" | result == null) {
				result = readTextFile();
				Log.d(">> NO RESULT >>> ", "Perhaps no network available. Reading from text file instead FEWL");
			}

			progressBar.setProgress(0);
			progressBar.setVisibility(-1);

			convertJsonString(result);
		}

	}

	// REMOVE BELOW THIS LINE

	public String readTextFile() {
		InputStream inputStream = getResources().openRawResource(R.raw.testjsonfeb4melbimperial);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		//byte[] b = null;
		int i;
		try {
			i = inputStream.read();
			while(i != -1) {
				outputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return outputStream.toString();
	}

	private void log(String msg) {
		Log.d(this.getClass().getSimpleName(), ">>> " + msg);
	}


}
