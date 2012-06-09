package de.stusti.GotoConsideredUseful;

import java.util.Date;

public class Event {

	private int calendar_id;
	private String title;
	private String description;
	private String location;
	
	private Date startDate;
	private Date endDate;
	
	public Event() {
		
	}

	public Event(int id, String title, String description, String location, Date start, Date end) {
		this.calendar_id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		
		this.startDate = start;
		this.endDate = end;
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

	public String getLocation() {
		return location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
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

	public void setLocation(String location) {
		this.location = location;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
