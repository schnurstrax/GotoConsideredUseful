package de.stusti.GotoConsideredUseful.calendars;

import java.util.ArrayList;

import de.stusti.GotoConsideredUseful.contacts.Address;


public class Event {

	private int calendar_id;
	private String title;
	private String description;
	private String locationFromCalendarEvent;
	private ArrayList<Address> locationProposals = new ArrayList<Address>();
	
	private long startDate;
	private long endDate;
	
	public Event() {
		
	}

	public Event(int id, String title, String description, String location, long start, long end) {
		this.calendar_id = id;
		this.title = title;
		this.description = description;
		this.locationFromCalendarEvent = location;
		
		this.startDate = start;
		this.endDate = end;
	}

	public ArrayList<String> getLocationStrings() {
		ArrayList<String> locations = new ArrayList<String>();
		
		if (locationFromCalendarIsSet()) {
			locations.add(getLocationFromCalendarEvent());
		}

		for (Address address: locationProposals) {
			locations.add(address.getAddressString());
		}

		return locations;		
	}	
	
	public boolean locationFromCalendarIsSet() {
		return ((null != locationFromCalendarEvent) && (locationFromCalendarEvent.trim().length() > 0)); 		
	}
	
	public void addLocationProposal(Address proposal) {
		this.locationProposals.add(proposal);
	}
	
	public void addLocationProposals(ArrayList<Address> locationProposals) {
		this.locationProposals.addAll(locationProposals);
	}
	
	public int getCalendarId() {
		return calendar_id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getLocationFromCalendarEvent() {
		return locationFromCalendarEvent;
	}

	public long getStartDate() {
		return startDate;
	}

	public long getEndDate() {
		return endDate;
	}
	
	public ArrayList<Address> getLocationProposals() {
		return locationProposals;
	}

	public void setCalendarId(int calendar_id) {
		this.calendar_id = calendar_id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocationFromCalendarEvent(String location) {
		this.locationFromCalendarEvent = location;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	
	public void setLocationProposals(ArrayList<Address> locationProposals) {
		this.locationProposals = locationProposals;
	}
}
