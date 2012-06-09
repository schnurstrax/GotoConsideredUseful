package de.stusti.GotoConsideredUseful;

import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MapViewActivity extends MapActivity {
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	}

}
