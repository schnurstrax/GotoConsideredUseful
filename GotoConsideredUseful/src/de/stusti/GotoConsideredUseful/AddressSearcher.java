package de.stusti.GotoConsideredUseful;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.maps.GeoPoint;

public class AddressSearcher {

	public AddressSearcher(Context context) {
		this.context = context;
	}
	
	protected Context context;

	public GeoPoint getNearestPointByAddress(String query) throws IOException {
		Location currentLocation = getBestLocation();
		List<GeoPoint> points = getPointsByAddress(query + " " + getCountry(currentLocation));

		return getNearest(currentLocation, points);
	}
	
	public String getCountryExtendedQuery(String query) throws IOException {
		Location currentLocation = getBestLocation();
		return query + " " + getCountry(currentLocation);
	}

	private List<GeoPoint> getPointsByAddress(String address) throws IOException {
	    Geocoder geocoder = new Geocoder(this.context);
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
		LocationManager locationManager = (LocationManager)this.context.getSystemService(Context.LOCATION_SERVICE);
		return locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
	}
	
	private String getCountry(Location location) throws IOException {
		Geocoder geocoder = new Geocoder(this.context);
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
