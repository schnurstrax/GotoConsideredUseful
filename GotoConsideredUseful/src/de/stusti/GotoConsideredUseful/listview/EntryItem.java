package de.stusti.GotoConsideredUseful.listview;


public class EntryItem implements Item{

	public final String title;
	public final String subtitle;

	public EntryItem(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
	}
	
	public boolean isSection() {
		return false;
	}

}
