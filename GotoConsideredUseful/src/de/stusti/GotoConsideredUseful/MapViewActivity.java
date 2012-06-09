package de.stusti.GotoConsideredUseful;

import java.io.IOException;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
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
	    
	    AddressOverlay itemizedoverlay = new AddressOverlay(drawable, this);
	    	
	    OverlayItem overlayitem; 
	    
		try {
			overlayitem = getOverlayByAddress(marker, "Test");
			itemizedoverlay.addOverlay(overlayitem);
		    mapOverlays.add(itemizedoverlay);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
	    
	}

	private OverlayItem getOverlayByAddress(String address, String name) throws IOException {
	    Geocoder geo = new Geocoder(this);
	    List<Address> addresses = geo.getFromLocationName(address, 5);
	    OverlayItem overlay = new OverlayItem(
	            new GeoPoint((int) (addresses.get(0).getLatitude() * 1E6),(int)(addresses.get(0).getLongitude()*1E6)),
	                name, "");
	    return overlay;
	}

}
