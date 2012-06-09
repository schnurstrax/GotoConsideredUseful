package de.stusti.GotoConsideredUseful;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

public class EventHandler {

	protected Activity activity;
	protected String[] fieldNames = new String[] {"calendar_id", "title",	"description", "dtstart", "dtend", "eventLocation"};
	
	public EventHandler(Activity activity) {
		this.activity = activity;
	}
	
	public ArrayList<Event> getEvents(Date date, int hours) {	
		
		Cursor cursor = getCursor(date, hours);	
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
		event.setStartDate(new Date(cursor.getLong(3)));
		event.setEndDate(new Date(cursor.getLong(4)));
		event.setLocation(cursor.getString(5));	
		
		return event;
	}
	
	protected Cursor getCursor(Date now, int hours) {		
		//Uri calendars = Uri.parse("content://com.android.calendar"+ "/calendars");
		Uri calendars = Uri.parse("content://com.android.calendar/events");

		String[] projection = fieldNames;
		
		Date start = new Date();
		Date end = new Date();
		end.setDate(10);
	    
	    String dtStart = Long.toString(start.getTime());
	    String dtEnd = Long.toString(end.getTime());
	    
		String selection = "((" + "dtstart" + " >= "+dtStart+") AND (" + "dtend" + " <= "+dtEnd+"))";
		
	    
	    //Date end = new Date(now.getYear(), now.getMonth(), now.getDay() , now.getHours() + hours, 59);	 
		//Date end = new Date(2012, 6, 7, 0, 0, 0);
		
		//Date start = new Date(2011, 6, 1, 0, 0, 0);
		//Date end = new Date(2012, 6, 7, 0, 0, 0);
//		
//		Date start = new Date();
//		Date end = new Date();
//		end.setDate(10);
//	    
//	    String dtStart = Long.toString(start.getTime());
//	    String dtEnd = Long.toString(end.getTime());
	    		
//	    t.set(59, 59, 23, t.monthDay, t.month, t.year);
//	    String dtEnd = Long.toString(t.toMillis(false));
//	    String[] selectionArgs = new String[] { dtStart, dtEnd };

	    
		//String selection = "dtstart='"+date+"'";	        
	    //String[] selectionArgs = null;	   
//		Time t = new Time();
//		t.set(0, 0, 0, t.monthDay, t.month, t.year);
//	    //t.setToNow();
//	    String dtStart = Long.toString(t.toMillis(false));
//	    t.set(59, 59, 23, t.monthDay, t.month, t.year);
//	    String dtEnd = Long.toString(t.toMillis(false));
//	    
//	    String[] selectionArgs = new String[] { dtStart, dtEnd };

		//String[] selectionArgs = new String[] {dtStart, dtEnd};
	    String[] selectionArgs = null;
	    String sortOrder = null;	
	    
		Cursor managedCursor = this.activity.managedQuery(calendars, projection, selection, selectionArgs, sortOrder);
		return managedCursor;
	}	
}
