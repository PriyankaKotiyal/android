package com.example.trackfinal;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Bt extends BroadcastReceiver{


@Override
public void onReceive(Context context, Intent intent) {

   Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

}
}