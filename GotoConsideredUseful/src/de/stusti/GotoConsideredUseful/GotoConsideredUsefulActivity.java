package de.stusti.GotoConsideredUseful;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class GotoConsideredUsefulActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

		EventHandler eventHandler = new EventHandler(this);
		Date today = new Date();
		int hours = 5;
		ArrayList<Event> events = eventHandler.getEvents(today, hours);
		 
		//ContactHandler contactHandler = new ContactHandler(this);
		
		TextView tv = new TextView(this);
		String titel = "";
		
		for(int i = 0; i<events.size(); i++) {
			titel += events.get(i).getTitle();
		}
		tv.setText(events.size() + "  Titel: " + titel);
			
		setContentView(tv);
	}
	
	protected void callGeo(String destination) {
    	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + destination));
        startActivity(i);
    }
	
	protected void callMap(String destination) {
		Intent intent = new Intent(this, MapViewActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("marker", destination);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}