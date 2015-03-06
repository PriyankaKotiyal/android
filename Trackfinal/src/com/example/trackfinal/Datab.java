package com.example.trackfinal;



import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Datab extends Activity {
	SQLiteDatabase db1 = null;
	private static String DBNAME = "PERSONSDETAILS.db";
		EditText e1;
		EditText e2;
		TextView t3;
		Editable nam,phn;
		Button b1;
		
		
		
		

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.datab);
			
			t3=(TextView)findViewById(R.id.textView3);
			b1=(Button)findViewById(R.id.button1);
		
			
		
			 db1 = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
			 Log.e("database","error");
			 try{
					db1.execSQL("CREATE TABLE IF NOT EXISTS datatable(FIRSTNAME VARCHAR, PHONENO VARCHAR); ");
					Log.e("database","error");
			 }catch(Exception e)
			 {
				 e.printStackTrace();
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
		public void ins(View v)
		{
			e1=(EditText)findViewById(R.id.editText1);
			e2=(EditText)findViewById(R.id.editText2);
			t3=(TextView)findViewById(R.id.textView3);
			nam=e1.getText();
			phn=e2.getText();
			try
			{
				db1.execSQL("INSERT INTO datatable(FIRSTNAME,PHONENO)  VALUES ('"+nam+"','"+phn+"');");
			}catch(Exception e){
				e.printStackTrace();
				
			}
			 t3.setText(nam+" "+phn);

			Toast.makeText(getApplicationContext(),"hello"+nam+" "+phn,Toast.LENGTH_LONG).show();}
		public void viewrec(View v)
		{
			t3=(TextView)findViewById(R.id.textView3);
			try{
				String str="";
			
				Cursor c = db1.rawQuery("SELECT * FROM  datatable", null);
				if(c!= null){
					if (c.moveToFirst()) {
						do {
							//whole data of column is fetched by getColumnIndex()
							String firstname =c.getString(c.getColumnIndex("FIRSTNAME"));
							String phone_no =c.getString(c.getColumnIndex("PHONENO"));
							
							System.out.println(firstname+""+phone_no);
							str+=firstname+" "+phone_no+"\n";
							}
						while(c.moveToNext());}
				//count the total number of entries
				int record_count =  c.getCount();
				System.out.println(record_count);
				str+="\nRecord count = "+record_count;
				t3.setText(str);
				//db1.close();
				//if you close the database then illegal exception will be occurred...
			}} catch(Exception e){
	System.out.println(e);
			
		
		
			}
		
		}
		public void deleteall(View v)
		{
			db1.execSQL("DELETE    FROM datatable ;");
		}
		public void del(View v)
		{
			e1=(EditText)findViewById(R.id.editText1);
			String n1=(String)e1.getText().toString();
			Toast.makeText(getApplicationContext(), n1,Toast.LENGTH_LONG).show();
		
				String txt="DELETE FROM datatable WHERE FIRSTNAME='"+n1+"';";
				//Toast.makeText(getApplicationContext(), txt,Toast.LENGTH_LONG).show();
	db1.execSQL(txt);
			
		}
	

}
