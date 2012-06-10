package de.stusti.GotoConsideredUseful.listview;

public class SectionItem implements Item{

	private final String title;
	
	public SectionItem(String title) {
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public boolean isSection() {
		return true;
	}

}
