package de.stusti.GotoConsideredUseful;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import de.stusti.GotoConsideredUseful.calendars.Event;
import de.stusti.GotoConsideredUseful.calendars.EventHandler;
import de.stusti.GotoConsideredUseful.contacts.Address;
import de.stusti.GotoConsideredUseful.contacts.ContactHandler;
import de.stusti.GotoConsideredUseful.listview.EntryAdapter;
import de.stusti.GotoConsideredUseful.listview.EntryItem;
import de.stusti.GotoConsideredUseful.listview.Item;
import de.stusti.GotoConsideredUseful.listview.SectionItem;
import de.stusti.GotoConsideredUseful.location.AddressSearcher;
import de.stusti.GotoConsideredUseful.location.MapViewActivity;

public class GotoMainActivity extends ListActivity {

	/** Called when the activity is first created. */

	ArrayList<Item> items = new ArrayList<Item>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArrayList<Event> events = getEvents();
		addEventsToItemList(events, items);
		EntryAdapter adapter = new EntryAdapter(this, items);
		setListAdapter(adapter);
	}

	protected ArrayList<Event> getEvents() {

		// Get events from calendar.
		EventHandler eventHandler = new EventHandler(this);
		Calendar calendar = Calendar.getInstance();

		long today = calendar.getTimeInMillis();
		int hours = 48;
		ArrayList<Event> events = eventHandler.getEvents(today, hours);
		// ArrayList<Event> eventsWithLocation =
		// eventHandler.getEventsWithLocation(today, hours);

		// Try to add locations using contact information.
		ContactHandler contactHandler = new ContactHandler(this);
		contactHandler.addLocationsToEvents(events);

		return events;
	}

	protected void addEventsToItemList(ArrayList<Event> events,
			ArrayList<Item> items) {

		for (Event event : events) {
			items.add(new SectionItem(event.getTitle(), event.getFormattedStartTime()));

			if (event.locationFromCalendarIsSet()) {
				items.add(new EntryItem(event.getLocationFromCalendarEvent(), ""));
			}
			
			ArrayList<Address> locationProposals = event.getLocationProposals();
			for (Address address : locationProposals) {
				items.add(new EntryItem(address.getStreet(), address.getPostCodeAndCity()));
			}
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		if (!items.get(position).isSection()) {

			EntryItem item = (EntryItem) items.get(position);
			String asdf = item.title;
			if (!(item.subtitle.equals("")))
				asdf = asdf + ", " + item.subtitle;
			callGeo(asdf);
		}

		super.onListItemClick(l, v, position, id);
	}

@Override
protected void onPause() {
	super.onPause();
	//remove gps 
	/*
	LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	locationManager.removeUpdates(this.locationListener);*/
}

protected void activateGPS() {
	/*
}
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
	*/
}


protected void onActivityResult(int i, int res, Intent asd) {
	Integer ress = new Integer(res);
	//boolean[] nums = new boolean[ress.bitCount()];
	// TODO den boolean-array abnehmen
	
}

public void callGeo(String destination){
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

public void callMap(String destination) {
	Intent intent = new Intent(this, MapViewActivity.class);
	Bundle bundle = new Bundle();
	bundle.putString("marker", destination);
	intent.putExtras(bundle);
	startActivity(intent);
}
}
