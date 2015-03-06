/**
 * 
 */
package com.example.trackfinal;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;


/**
 * @author Saket
 *
 */
public class serv extends Service {
	
	
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	//@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		 super.onStartCommand(intent, flags, startId);
	/*	 Notification not=new Notification.Builder(getApplicationContext())
	.setContentTitle("Circles Initiated")
	.setContentText("Black")
	.setSmallIcon(R.drawable.ic_launcher)
	.build();
	startForeground(1234, not);
	*/
		 Intent resultIntent=new Intent(this,MainActivity.class);
		 PendingIntent pendin=PendingIntent.getBroadcast(this,0, resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
		 NotificationCompat.Builder mbuilder=new NotificationCompat.Builder(this)
		 .setSmallIcon(R.drawable.ic_launcher)
		 .setContentTitle("Circles")
		 .setWhen(System.currentTimeMillis())
		 .setContentIntent(pendin)
		 .setContentText("Initiated safety");
		 mbuilder.setContentIntent(pendin);
		 NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		 nm.notify(2,mbuilder.build());
		 
		 
		return Service.START_STICKY;
		
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
	}
	

}
