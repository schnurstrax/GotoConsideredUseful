package de.stusti.GotoConsideredUseful;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class GotoConsideredUsefulActivity extends Activity {
	
	protected ListView listView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

	  	// Get events from calendar.
		EventHandler eventHandler = new EventHandler(this);
		Calendar calendar = Calendar.getInstance();		
		
		long today = calendar.getTimeInMillis();
		int hours = 548;
		ArrayList<Event> events = eventHandler.getEvents(today, hours);
		//ArrayList<Event> eventsWithLocation = eventHandler.getEventsWithLocation(today, hours);		
		
		// Try to add locations using contact information.
		ContactHandler contactHandler = new ContactHandler(this);
		contactHandler.addLocationsToEvents(events);
		
		// Convert ArrayList to Event-Array.
		Event []eventArray = new Event[events.size()];
		events.toArray(eventArray);
		
		// Add events to the listView.
	  	setContentView(R.layout.main2);	  	
		adapterThingy adap = new adapterThingy(this, R.layout.termin_listitem, eventArray);
		listView = (ListView)findViewById(R.id.listView1);       
        final View header = (View)getLayoutInflater().inflate(R.layout.termin_listitem, null);
        
        
        listView.addHeaderView(header);    
        listView.setAdapter(adap);
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