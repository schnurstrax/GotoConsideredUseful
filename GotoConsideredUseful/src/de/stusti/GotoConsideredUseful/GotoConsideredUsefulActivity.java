package de.stusti.GotoConsideredUseful;

import android.app.Activity;

public class GotoConsideredUsefulActivity extends Activity {

//	protected ListView listView;
//	protected LocationListener locationListener;
//	
//	GotoConsideredUsefulActivity ggg = this;
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//	  super.onCreate(savedInstanceState);
//	  	
//	  	activateGPS();
//
//	  	// Get events from calendar.
//		EventHandler eventHandler = new EventHandler(this);
//		Calendar calendar = Calendar.getInstance();		
//		
//		long today = calendar.getTimeInMillis();
//		int hours = 48;
//		ArrayList<Event> events = eventHandler.getEvents(today, hours);
//		//ArrayList<Event> eventsWithLocation = eventHandler.getEventsWithLocation(today, hours);		
//		
//		// Try to add locations using contact information.
//		ContactHandler contactHandler = new ContactHandler(this);
//		contactHandler.addLocationsToEvents(events);
//		
//		// Convert ArrayList to Event-Array.
//		Event []eventArray = new Event[events.size()];
//		events.toArray(eventArray);
//		
//		// Add events to the listView.
//	  	setContentView(R.layout.main2);	  	
//		adapterThingy adap = new adapterThingy(this, R.layout.termin_listitem, eventArray);
//		listView = (ListView)findViewById(R.id.listView1);       
//		View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
//		ImageView b = (ImageView)header.findViewById(R.id.settings);
////		b.setOnClickListener(new View.OnClickListener() {
////			
//////			public void onClick(View v) {
//////				Intent intent = new Intent(ggg, chooseCalendar.class);
//////				intent.putExtra("calendars", new String[]{"",""});//TODO String Array Reein
//////				startActivityForResult(intent,1);
//////				
//////			}
////		});
//        listView.addHeaderView(header);    
//        listView.setAdapter(adap);
//	}
//	
//	@Override
//	protected void onPause() {
//		super.onPause();
//		//remove gps 
//		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//		locationManager.removeUpdates(this.locationListener);
//	}
//	
//	protected void activateGPS() {
//		// Acquire a reference to the system Location Manager
//		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//
//		// Define a listener that responds to location updates
//		this.locationListener = new LocationListener() {
//		    public void onLocationChanged(Location location) {
//		      // Called when a new location is found by the network location provider.
//		      makeUseOfNewLocation(location);
//		    }
//
//		    private void makeUseOfNewLocation(Location location) {}
//
//			public void onStatusChanged(String provider, int status, Bundle extras) {}
//
//		    public void onProviderEnabled(String provider) {}
//
//		    public void onProviderDisabled(String provider) {}
//		  };
//
//		// Register the listener with the Location Manager to receive location updates
//		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 0, this.locationListener);
//	}
//
//	protected void onActivityResult(int i, int res, Intent asd) {
//		Integer ress = new Integer(res);
//		//boolean[] nums = new boolean[ress.bitCount()];
//		// TODO den boolean-array abnehmen
//		
//	}
//	
//	protected void callGeo(String destination){
//		AddressSearcher addressSearcher = new AddressSearcher(this);
//		String query = destination;
//		
//		try {
//			query = addressSearcher.getCountryExtendedQuery(destination);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//    	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + query));
//        startActivity(i);
//    }
//	
//	protected void callMap(String destination) {
//		Intent intent = new Intent(this, MapViewActivity.class);
//		Bundle bundle = new Bundle();
//		bundle.putString("marker", destination);
//		intent.putExtras(bundle);
//		startActivity(intent);
//	}

//	protected ListView listView;
//	protected LocationListener locationListener;
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//	    setContentView(R.layout.asdfxml);
//	    activateGPS();
//
//	  	// Get events from calendar.
//		EventHandler eventHandler = new EventHandler(this);
//		Calendar calendar = Calendar.getInstance();		
//		
//		long today = calendar.getTimeInMillis();
//		int hours = 548;
//		ArrayList<Event> events = eventHandler.getEvents(today, hours);
//		//ArrayList<Event> eventsWithLocation = eventHandler.getEventsWithLocation(today, hours);		
//		
//		// Try to add locations using contact information.
//		ContactHandler contactHandler = new ContactHandler(this);
//		contactHandler.addLocationsToEvents(events);
//		
//		// Convert ArrayList to Event-Array.
//		Event []eventArray = new Event[events.size()];
//		events.toArray(eventArray);
//	    GridView gridview = (GridView) findViewById(R.id.gridview);
//	    gridview.setAdapter(new newAdapter(this, events));
//	}
//	
//	
//	
	
	
	
	/*protected ListView listView;
	protected LocationListener locationListener;
	
	GotoConsideredUsefulActivity ggg = this;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  	
	  	activateGPS();

	  	// Get events from calendar.
		EventHandler eventHandler = new EventHandler(this);
		Calendar calendar = Calendar.getInstance();		
		
		long today = calendar.getTimeInMillis();
		int hours = 48;
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
//	*/
//	@Override
//	protected void onPause() {
//		super.onPause();
//		//remove gps 
//		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//		locationManager.removeUpdates(this.locationListener);
//	}
//	
//	protected void activateGPS() {
//		// Acquire a reference to the system Location Manager
//		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//
//		// Define a listener that responds to location updates
//		this.locationListener = new LocationListener() {
//		    public void onLocationChanged(Location location) {
//		      // Called when a new location is found by the network location provider.
//		      makeUseOfNewLocation(location);
//		    }
//
//		    private void makeUseOfNewLocation(Location location) {}
//
//			public void onStatusChanged(String provider, int status, Bundle extras) {}
//
//		    public void onProviderEnabled(String provider) {}
//
//		    public void onProviderDisabled(String provider) {}
//		  };
//
//		// Register the listener with the Location Manager to receive location updates
//		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 0, this.locationListener);
//	}
//	
//
//	protected void onActivityResult(int i, int res, Intent asd) {
//		Integer ress = new Integer(res);
//		//boolean[] nums = new boolean[ress.bitCount()];
//		// TODO den boolean-array abnehmen
//		
//	}
//	
//	protected void callGeo(String destination){
//		AddressSearcher addressSearcher = new AddressSearcher(this);
//		String query = destination;
//		
//		try {
//			query = addressSearcher.getCountryExtendedQuery(destination);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//    	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + query));
//        startActivity(i);
//    }
//	
//	protected void callMap(String destination) {
//		Intent intent = new Intent(this, MapViewActivity.class);
//		Bundle bundle = new Bundle();
//		bundle.putString("marker", destination);
//		intent.putExtras(bundle);
//		startActivity(intent);
//	}
}