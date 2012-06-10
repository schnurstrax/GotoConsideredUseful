package de.stusti.GotoConsideredUseful.listview;

import java.util.ArrayList;

import de.stusti.GotoConsideredUseful.calendars.Event;
import de.stusti.GotoConsideredUseful.contacts.Contact;

public class SectionItem implements Item{

	private final String title;
	private final String additionalInfo;
	private final long eventId;
	private final ArrayList<Contact> contactProposals;
	
	public SectionItem(String title, String additionalInfo, Event event) {
		this.title = title;
		this.additionalInfo = additionalInfo;
		this.eventId = event.getEventId();
		this.contactProposals = event.getContactProposals();
	}
	
	public String getTitle(){
		return title;
	}
	
	public long getEventId(){
		return eventId;
	}
	
	public ArrayList<Contact> getEventContactProposals(){
		return contactProposals;
	}	
	
	public String getAdditionalInfo(){
		return additionalInfo;
	}
	
	public boolean isSection() {
		return true;
	}

}
