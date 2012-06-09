package de.stusti.GotoConsideredUseful;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class GotoConsideredUsefulActivity extends Activity {
	
	protected ListView listView;
	protected LocationListener locationListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  	
	  	activateGPS();

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
        listView.addHeaderView(header);    
        listView.setAdapter(adap);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		locationManager.removeUpdates(this.locationListener);
	}
	
	protected void activateGPS() {
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		this.locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {
		      // Called when a new location is found by the network location provider.
		      makeUseOfNewLocation(location);
		    }

		    private void makeUseOfNewLocation(Location location) {}

			public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		  };

		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 0, this.locationListener);
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