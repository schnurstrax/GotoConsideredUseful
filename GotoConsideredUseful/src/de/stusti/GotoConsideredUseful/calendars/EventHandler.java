package de.stusti.GotoConsideredUseful.calendars;

import java.util.ArrayList;


import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class EventHandler {

	protected Activity activity;
	protected String[] fieldNames = new String[] {"calendar_id", "title", "description", "dtstart", "dtend", "eventLocation", "begin", "_id"};
	private ArrayList<String> notAllowedIds = new ArrayList<String>();
	private String[] calenderNames;
	private String[] calendarIds;
	
	public EventHandler(Activity activity) {
		this.activity = activity;
	}
	
	/**
	 * adds the specific id to the ignore list
	 * @param id
	 */
	public void setnotAllowedId(String id){
		this.notAllowedIds.add(id);
	}
	
	/**
	 * add all ids to the ignore list who match the entries with false in the noIDs list.
	 * the noIds list have to has the same length as the calendarIds and the calendarNames list. 
	 * 
	 * @param notIds
	 */
	public void setnotAllowedIds(Boolean[] notIds){
		if(notIds.length != calendarIds.length) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < calendarIds.length; i++) {
			if (!(notIds[i])) {
				this.notAllowedIds.add(calendarIds[i]);
			}
			
		}
		
	}
	
	/**
	 * afterwards ignorelist is empty
	 */
	public void clearIgnoreList(){
		this.notAllowedIds = new ArrayList<String>();
	}
	
	/**
	 * add the id of calendars we don't want to search to selection-clauses
	 * @return
	 */
	private String getSelectionWithoutIds() {
		String selection = "";
		if(notAllowedIds.size() != 0){
			selection = "NOT (";
			for(String notAllowedId : notAllowedIds){
				selection += "(calendar_id = '"+notAllowedId+"') OR";
			}
			selection = selection.substring(0, selection.length()-3);
			selection += ")";
			
		}
		return selection;
	}
	
	/**
	 * get the names of all calendars for this device
	 * Notice: list calenderNames and calenderIds are empty before calling this methode
	 * @return
	 */
	public String[] getAllCalendarNames(){
		Cursor cursor = this.activity.getContentResolver().query(Uri.parse("content://com.android.calendar/calendars"),
                (new String[] { "_id", "displayName"}), null, null, null);
		if(cursor.getCount() != 0){
			int numberOfEvents = cursor.getCount();	
			this.calenderNames = new String[numberOfEvents];
			this.calendarIds = new String[numberOfEvents];
			
			cursor.moveToFirst();		  
			for (int i = 0; i < numberOfEvents; i++) {	
				this.calendarIds[i] = cursor.getString(0);
				this.calenderNames[i] = cursor.getString(1);
				Log.v("cal", "id" + cursor.getString(0) + " name " + cursor.getString(1));
		        cursor.moveToNext();
		    }		
		    cursor.close();
		    return this.calenderNames;
		}
		return null;
	}
	
	/**
	 * returns all Calendar-Events in the specific time starting at now.
	 * all calendars in the ignore list will be ignored
	 * 
	 * @param now
	 * @param hours
	 * @return
	 */
	public ArrayList<Event> getEvents(long now, int hours) {	
		
		Cursor cursor = getCursorUsingInstances(now, hours);	
		int numberOfEvents = cursor.getCount();		
		//Log.v("CURSOR", "" + numberOfEvents);
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
		event.setLocationFromCalendarEvent(cursor.getString(5));	
		event.setBegin(cursor.getLong(6));
		event.setEventId(cursor.getInt(7));	
		
		return event;
	}
	
	/**
	 * a cursor is  pointer to a part of the database specified by the query
	 * @param now
	 * @param hours
	 * @return
	 */
	protected Cursor getCursorUsingInstances(long now, int hours) {	
		String selection =getSelectionWithoutIds();
		String[] selectionArgs = null;
		
		String path = "instances/when/" + (now) + "/" + (now + getMillisecondsFromHours(hours));
		String sortOrder = "begin ASC";
		
		Cursor managedCursor = getCalendarManagedCursor(getProjection(), selection, path, sortOrder, selectionArgs);
		return managedCursor;
	}
	
	public ArrayList<Event> getEventsWithLocation(long now, int hours) {
		Cursor cursor = getCursorWithLocationUsingInstances(now, hours);	
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
	
	
	protected Cursor getCalendarManagedCursor(String[] projection, String selection, String path, String sort, String[] selectionArgs) {
	    
		Uri calendars = Uri.parse("content://calendar/" + path);
	    Cursor managedCursor = null;
	   
	    try {
	        managedCursor = this.activity.getContentResolver().query(calendars, projection, selection, selectionArgs, sort);
	    } catch (IllegalArgumentException e) {
	        Log.w("DEBUG", "Failed to get provider at [" + calendars.toString() + "]");
	    }

	    if (managedCursor == null) {
	        // try again
	        calendars = Uri.parse("content://com.android.calendar/" + path);
	        try {
	            managedCursor = this.activity.getContentResolver().query(calendars,
	                    projection, selection, selectionArgs, sort);
	        } catch (IllegalArgumentException e) {
	            Log.w("DEBUG", "Failed to get provider at [" + calendars.toString()+ "]");
	        }
	    }
	    
	    return managedCursor;
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
	
	protected Cursor getCursorWithLocationUsingInstances(long now, int hours) {		
	    
		String selection = "eventLocation NOT NULL";
		String path = "instances/when/" + (now) + "/" + (now + getMillisecondsFromHours(hours));
		String sortOrder = "begin ASC";
		Cursor managedCursor = getCalendarManagedCursor(getProjection(), selection, path, sortOrder, null);
		
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
		long end = now + getMillisecondsFromHours(hours);
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
	
	protected long getMillisecondsFromHours(int hours){
		return hours*60*60*1000;
	}
}