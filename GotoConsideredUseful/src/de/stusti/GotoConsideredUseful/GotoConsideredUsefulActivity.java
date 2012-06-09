package de.stusti.GotoConsideredUseful;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.*;

public class GotoConsideredUsefulActivity extends FragmentActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

		setContentView(R.layout.hauptteil);
		EventHandler eventHandler = new EventHandler(this);
<<<<<<< HEAD
		Date today = new Date();
		ArrayList<Event> events = eventHandler.getEvents(today);
		frag f;
		FragmentTransaction fg = getSupportFragmentManager().beginTransaction();
		for (Event e:events) {
		f = new frag(e.getTitle(), e.getDescription());
		fg.add(R.id.linearStuff, f);
		}
		fg.commit();
		

=======
		Calendar calendar = Calendar.getInstance();		
		long today = calendar.getTimeInMillis();
		int hours = 48;
		ArrayList<Event> events = eventHandler.getEvents(today, hours);
		 
		//ContactHandler contactHandler = new ContactHandler(this);
		
		TextView tv = new TextView(this);
		String title = "\n";

	    for(Event event: events) {
	    	title += event.getTitle() + "\n";
		}
		tv.setText(events.size() + " Titel: " + title);
			
		setContentView(tv);
>>>>>>> b674c05cf6fd0dbcb9950a1b44138904b4138632
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