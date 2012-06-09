package de.stusti.GotoConsideredUseful;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class GotoConsideredUsefulActivity extends Activity {
	private ListView listView1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

		EventHandler eventHandler = new EventHandler(this);
		Calendar calendar = Calendar.getInstance();		
		long today = calendar.getTimeInMillis();
		int hours = 48;
		ArrayList<Event> events = eventHandler.getEvents(today, hours);
		ArrayList<Event> eventsWithLocation = eventHandler.getEventsWithLocation(today, hours);
		 
		//ContactHandler contactHandler = new ContactHandler(this);
		
		//TextView tv = new TextView(this);
		//String title = "\n";

	    //for(Event event: events) {
	    //	title += event.getTitle() + "\n";
		//}
		//tv.setText(events.size() + "   " + eventsWithLocation.size() + " Titel: " + title);
			
		//setContentView(tv);
		Event[] ev = new Event[events.size()];
		for (int i=0; i<events.size(); i++) {
			ev[i] = events.get(i);
		}
		adapterThingy adap = new adapterThingy(this, R.layout.termin_listitem, ev);
		
		listView1 = (ListView)findViewById(R.id.listView1);
        
        View header = (View)getLayoutInflater().inflate(R.layout.termin_listitem, null);
        listView1.addHeaderView(header);
       
        listView1.setAdapter(adap);
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