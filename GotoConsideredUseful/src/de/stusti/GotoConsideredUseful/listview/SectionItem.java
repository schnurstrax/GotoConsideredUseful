package de.stusti.GotoConsideredUseful.listview;

public class SectionItem implements Item{

	private final String title;
	private final String additionalInfo;
	
	public SectionItem(String title, String additionalInfo) {
		this.title = title;
		this.additionalInfo = additionalInfo;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getAdditionalInfo(){
		return additionalInfo;
	}
	
	public boolean isSection() {
		return true;
	}

}
