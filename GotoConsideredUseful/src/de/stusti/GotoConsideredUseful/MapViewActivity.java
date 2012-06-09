package de.stusti.GotoConsideredUseful;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapViewActivity extends MapActivity {
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    Bundle bundle = getIntent().getExtras();
	    String marker = bundle.getString("marker");
	    
	    setContentView(R.layout.main);
	    
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    
	    List<Overlay> mapOverlays = mapView.getOverlays();

	    Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
	    
	    AddressOverlay itemizedOverlay = new AddressOverlay(drawable, this);
	    
	    AddressSearcher addressSearcher = new AddressSearcher(this);
	    
		try {
			OverlayItem overlayItem = new OverlayItem(addressSearcher.getNearestPointByAddress(marker), marker, "");
			itemizedOverlay.addOverlay(overlayItem);
		    mapOverlays.add(itemizedOverlay);
		    mapView.getController().setCenter(overlayItem.getPoint());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}
}
