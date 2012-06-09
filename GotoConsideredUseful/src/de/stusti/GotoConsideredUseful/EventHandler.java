package de.stusti.GotoConsideredUseful;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

public class EventHandler {

	protected Activity activity;
	protected String[] fieldNames = new String[] {"calendar_id", "title", "description", "dtstart", "dtend", "eventLocation"};
	
	public EventHandler(Activity activity) {
		this.activity = activity;
	}
	
	public ArrayList<Event> getEvents(long now, int hours) {	
		
		Cursor cursor = getCursor(now, hours);	
		int numberOfEvents = cursor.getCount();		
		ArrayList<Event> events = new ArrayList<Event>();

		cursor.moveToFirst();		  
		for (int i = 0; i < numberOfEvents; i++) {		
			Event event = getEventFromCurrentPosition(cursor);
			events.add(event);		
	        cursor.moveToNext();
	    }		
	    cursor.close();
		return events;
	}
	
	public ArrayList<Event> getEventsWithLocation(long now, int hours) {
		Cursor cursor = getCursorWithLocation(now, hours);	
		int numberOfEvents = cursor.getCount();		
		ArrayList<Event> events = new ArrayList<Event>();

		cursor.moveToFirst();		  
		for (int i = 0; i < numberOfEvents; i++) {		
			Event event = getEventFromCurrentPosition(cursor);
			events.add(event);		
	        cursor.moveToNext();
	    }		
	    cursor.close();
		return events;
	}
	
	
	protected Event getEventFromCurrentPosition(Cursor cursor) {
		Event event = new Event();		
		
		event.setCalendarId(cursor.getInt(0));
		event.setTitle(cursor.getString(1));
		event.setDescription(cursor.getString(2));
		event.setStartDate(cursor.getLong(3));
		event.setEndDate(cursor.getLong(4));
		event.setLocation(cursor.getString(5));	
		
		return event;
	}
	
	protected Cursor getCursor(long now, int hours) {				

		Cursor managedCursor = this.activity.managedQuery(getCalendarsUri(), 
				getProjection(),  
				getSelectionForTimeInterval(now, hours), 
				getEmptySelectionArgs(), 
				getSortOrderStartDateAscending());
		return managedCursor;
	}	
	
	protected Cursor getCursorWithLocation(long now, int hours) {		
	    
		Cursor managedCursor = this.activity.managedQuery(getCalendarsUri(), 
				getProjection(), 
				getSelectionForTimeIntervalWithLocation(now, hours), 
				getEmptySelectionArgs(), 
				getSortOrderStartDateAscending());
		return managedCursor;
	}	
	
	protected Uri getCalendarsUri() {
		return Uri.parse("content://com.android.calendar/events");
	}
	
	protected String[] getProjection() {
		return fieldNames;
	}
	
	protected String getSelectionForTimeInterval(long now, int hours)
	{
		long end = now + hours*60*60*1000;		
	    String dtStart = Long.toString(now);
	    String dtEnd = Long.toString(end);
        
	    String selection = "((" + "dtstart" + " >= '"+dtStart+"') AND (" + "dtend" + " <= '"+dtEnd+"') AND ( 0 < '"+dtEnd+"'))";
	    return selection;
	}
	
	protected String getSelectionForTimeIntervalWithLocation(long now, int hours)
	{
	    String selection = "(" + getSelectionForTimeInterval(now, hours) + " AND (eventLocation NOT NULL))";	    
	    return selection;
	}
	
	
	protected String getSortOrderStartDateAscending(){
		 return "dtstart ASC";
	}
	
	protected String[] getEmptySelectionArgs() {
		String[] selectionArgs = new String[] {};
		return selectionArgs;
	}
}