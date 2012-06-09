package de.stusti.GotoConsideredUseful;


public class Event {

	private int calendar_id;
	private String title;
	private String description;
	private String location;
	
	private long startDate;
	private long endDate;
	
	public Event() {
		
	}

	public Event(int id, String title, String description, String location, long start, long end) {
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

	public long getStartDate() {
		return startDate;
	}

	public long getEndDate() {
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

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

}
