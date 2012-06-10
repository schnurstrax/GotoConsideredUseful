package de.stusti.GotoConsideredUseful.calendars;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.stusti.GotoConsideredUseful.contacts.Address;
import de.stusti.GotoConsideredUseful.contacts.Contact;


public class Event {

	private int calendar_id;
	private int event_id;
	private String title;
	private String description;
	private String locationFromCalendarEvent;
	private ArrayList<Address> locationProposals = new ArrayList<Address>();
	private ArrayList<Contact> contactProposals = new ArrayList<Contact>();
	
	private long startDate;
	private long endDate;
	private long begin;
	
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

	public String getFormattedStartTime() {		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(getBegin());
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String formattedStartTime = dateFormat.format(calendar.getTime()) + " Uhr";
		
		return formattedStartTime;
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
		return ((null != getLocationFromCalendarEvent()) && (getLocationFromCalendarEvent().trim().length() > 0)); 		
	}
	
	public void addLocationProposal(Address proposal) {
		this.locationProposals.add(proposal);
	}
	
	public void addLocationProposals(ArrayList<Address> locationProposals) {
		this.locationProposals.addAll(locationProposals);
	}
	
	public void addContactProposal(Contact proposal) {
		this.contactProposals.add(proposal);
	}
	
	public void addContactProposals(ArrayList<Contact> contactProposals) {
		this.contactProposals.addAll(contactProposals);
	}
	
	public int getCalendarId() {
		return calendar_id;
	}
	
	public int getEventId() {
		return event_id;
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
	
	public long getBegin() {
		return begin;
	}
	
	public ArrayList<Address> getLocationProposals() {
		return locationProposals;
	}

	public ArrayList<Contact> getContactProposals() {
		return contactProposals;
	}
	
	public void setCalendarId(int calendar_id) {
		this.calendar_id = calendar_id;
	}
	
	public void setEventId(int event_id) {
		this.event_id = event_id;
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
	
	public void setBegin(long begin){
		this.begin = begin;
	}
	
}
