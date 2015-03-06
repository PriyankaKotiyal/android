package com.example.trackfinal;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Trackmemain extends Activity implements GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener,
LocationListener{
	
	private LocationManager locationManager;
	private String provider;
	String display="";
	Location location ;
	
	SQLiteDatabase db1 = null;
	String DBNAME = "PERSONSDETAILS.db";
		
		
		
		
		
		String str[]=new String[5];
		int flag=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 db1 = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
		setContentView(R.layout.tra);
		getloc();
	}

	

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
	public void onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		String msg = "Updated Location: " +
                Double.toString(loc.getLatitude()) + "," +
                Double.toString(loc.getLongitude());
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		Geocoder geo=new Geocoder(getBaseContext(),Locale.getDefault());
		display="";
		try
		{
			List<Address> address=geo.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
			if(address.size()>0)
			{
				for(int i=0;i<address.get(0).getMaxAddressLineIndex();i++)
				{
				
					display+=address.get(0).getAddressLine(i)+"\n";
				}
				Toast.makeText(this,display, Toast.LENGTH_SHORT).show();
				btn();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
	}
	public void getloc()
	{
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//Toast.makeText(getApplicationContext(), locationManager+"", Toast.LENGTH_LONG).show();
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		//Toast.makeText(getApplicationContext(), provider+"", Toast.LENGTH_LONG).show();
		
		location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			Toast.makeText(getApplicationContext(), "Provider " + provider + " has been selected.", Toast.LENGTH_LONG).show();
			onLocationChanged(location);
		} else {
			Toast.makeText(getApplicationContext(), "Location nt avaliabe ", Toast.LENGTH_LONG).show();
			display="LOcation nt available";
			btn();
		}

	}
	public void btn()
	{
		try{
			
			Cursor c = db1.rawQuery("SELECT * FROM  datatable", null);
			if(c!= null){
				if (c.moveToFirst()) {
					do {
						//whole data of column is fetched by getColumnIndex()
						
						String phone_no =c.getString(c.getColumnIndex("PHONENO"));
						str[flag]=phone_no;
						flag++;
												}
					while(c.moveToNext());}
			//count the total number of entries
			
			
			
			
			//db1.close();
			//if you close the database then illegal exception will be occurred...
		}} catch(Exception e){
System.out.println(e);
		
	
	
		}
		for(int k=0;k<flag;k++){
		try
		{
			String dang="I am In Danger , HELP ME ! "+display;
			SmsManager smsManager = SmsManager.getDefault(); 
			smsManager.sendTextMessage(str[k], null, dang, null, null);
			Toast.makeText(getApplicationContext(), str[k],Toast.LENGTH_LONG).show();
			Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
			
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show(); e.printStackTrace();
		}
		}
	}



	

		

}

