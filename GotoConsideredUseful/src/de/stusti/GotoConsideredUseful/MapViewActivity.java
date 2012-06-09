package de.stusti.GotoConsideredUseful;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import android.app.AlertDialog;
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
	    
		try {
			OverlayItem overlayItem = new OverlayItem(getNearestPointByAddress(marker), "marker", "");
			itemizedOverlay.addOverlay(overlayItem);
		    mapOverlays.add(itemizedOverlay);
		    mapView.getController().setCenter(overlayItem.getPoint());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}
	
	private GeoPoint getNearestPointByAddress(String marker) throws IOException {
		Location currentLocation = getBestLocation();
		List<GeoPoint> points = getPointsByAddress(marker + " " + getCountry(currentLocation));

		return getNearest(currentLocation, points);
	}

	private List<GeoPoint> getPointsByAddress(String address) throws IOException {
	    Geocoder geocoder = new Geocoder(this);
	    List<Address> addresses = geocoder.getFromLocationName(address, 20);	  
	    
	    return convertAddressesToPoints(addresses);
	}
	
	private List<GeoPoint> convertAddressesToPoints(List<Address> addresses) {
		List<GeoPoint> points = new LinkedList<GeoPoint>();
		
		for (Address address: addresses) {
			points.add(new GeoPoint((int) (address.getLatitude() * 1E6), (int)(address.getLongitude()*1E6)));
		}
		
		return points;
	}
	
	private Location getBestLocation() {
		LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		return locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
	}
	
	private String getCountry(Location location) throws IOException {
		Geocoder geocoder = new Geocoder(this);
	    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
	    return addresses.get(0).getCountryName();
	}
	
	private GeoPoint getNearest(Location location, List<GeoPoint> points) {
		float[] result = new float[3];
		float distance;
		float minDistance = Float.POSITIVE_INFINITY;
		GeoPoint nearestPoint = null;
		
		for (GeoPoint point: points) {
			Location.distanceBetween(location.getLatitude(), location.getLongitude(), point.getLatitudeE6()/1E6, point.getLongitudeE6()/1E6, result);
			distance = result[0];
			if (distance < minDistance) {
				minDistance = distance;
				nearestPoint = point;
			}
		}
		
		return nearestPoint;
	}

}
