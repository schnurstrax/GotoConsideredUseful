package de.stusti.GotoConsideredUseful;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class GotoConsideredUsefulActivity extends Activity {
	
	protected ListView listView;
	GotoConsideredUsefulActivity ggg = this;
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
		
		// Convert ArrayList to Event-Array.
		Event []eventArray = new Event[events.size()];
		events.toArray(eventArray);
		
		// Add events to the listView.
	  	setContentView(R.layout.main2);	  	
		adapterThingy adap = new adapterThingy(this, R.layout.termin_listitem, eventArray);
		listView = (ListView)findViewById(R.id.listView1);       
		View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
		ImageView b = (ImageView)header.findViewById(R.id.settings);
		b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(ggg, chooseCalendar.class);
				intent.putExtra("calendars", new String[]{"",""});//TODO String Array Reein
				startActivityForResult(intent,1);
				
			}
		});
        listView.addHeaderView(header);    
        listView.setAdapter(adap);
	}
	
	protected void onActivityResult(int i, int res, Intent asd) {
		Integer ress = new Integer(res);
		//boolean[] nums = new boolean[ress.bitCount()];
		// TODO den boolean-array abnehmen
		
	}
	
	protected void callGeo(String destination){
		AddressSearcher addressSearcher = new AddressSearcher(this);
		String query = destination;
		
		try {
			query = addressSearcher.getCountryExtendedQuery(destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + query));
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