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
		ArrayList<Event> events = eventHandler.getEvents(today);
		 
		//ContactHandler contactHandler = new ContactHandler(this);
		
		TextView tv = new TextView(this);
		tv.setText("Titel: " + events.get(0).getTitle());
		setContentView(tv);
	}
	
	protected void callGeo(String destination) {
    	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + destination));
        startActivity(i);
    }
}