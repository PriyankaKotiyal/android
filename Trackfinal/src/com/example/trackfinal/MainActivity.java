package com.example.trackfinal;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements SensorEventListener {
	Sensor acceloremeter;
	SensorManager sm;
	int ctr=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startService(new Intent(getBaseContext(), serv.class));
		sm=(SensorManager) getSystemService(SENSOR_SERVICE);
		acceloremeter=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sm.registerListener(this, acceloremeter,SensorManager.SENSOR_DELAY_NORMAL);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	public void data(View v){
		Intent inte=new Intent(getApplicationContext(),Datab.class);
		startActivity(inte);
	}
	

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.values[0]>19)
		{
			ctr++;
			if(ctr>11)
			{
			Toast.makeText(getApplicationContext(), (event.values[0]+" "+event.values[1]+" "+event.values[2]+" "), Toast.LENGTH_LONG).show();
		
		gothen();
		
			ctr=0;
			
			}
		}
	}
	public void gothen()
	{
		Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
		Intent intes=new Intent(getApplicationContext(),Trackmemain.class);
				startActivity(intes);
	}
	
	
}
