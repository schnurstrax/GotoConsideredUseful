package de.stusti.GotoConsideredUseful.listview;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import de.stusti.GotoConsideredUseful.GotoMainActivity;
import de.stusti.GotoConsideredUseful.R;

public class EntryAdapter extends ArrayAdapter<Item> {

	private Context context;
	private ArrayList<Item> items;
	private LayoutInflater vi;
	private EntryAdapter thishere;

	public EntryAdapter(Context context,ArrayList<Item> items) {
		super(context,0, items);
		thishere = this;
		this.context = context;
		this.items = items;
		vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		final Item i = items.get(position);
		if (i != null) {
			if(i.isSection()){
				SectionItem si = (SectionItem)i;
				v = vi.inflate(R.layout.list_item_section, null);

				v.setOnClickListener(null);
				v.setOnLongClickListener(null);
				v.setLongClickable(false);
				
				final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
				sectionView.setText(si.getTitle());

				final TextView sectionViewAdditionalInfo = (TextView) v.findViewById(R.id.list_item_section_text_additional_info);
				sectionViewAdditionalInfo.setText(si.getAdditionalInfo());
				
				v.findViewById(R.id.list_item_entry_drawable_phonecall).setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {			
						//long id = si.getEventId();
						//thishere.editCalendarEvent(id);
						
					}
				});
				
	
			}else{
				EntryItem ei = (EntryItem)i;
				v = vi.inflate(R.layout.list_item_entry, null);
				final TextView title = (TextView)v.findViewById(R.id.list_item_entry_title);
				final TextView subtitle = (TextView)v.findViewById(R.id.list_item_entry_summary);
				
				if (title != null) 
					title.setText(ei.title);
				if(subtitle != null)
					subtitle.setText(ei.subtitle);
				final EntryItem ei2 = ei;
				
				v.findViewById(R.id.list_item_entry_drawable).setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						GotoMainActivity g = (GotoMainActivity) context;
						String asdf = ei2.title;
						if (!(ei2.subtitle.equals("")))
							asdf = asdf + ", " + ei2.subtitle;
						g.callMap(asdf);
						
					}
				});
			}
		}
		return v;
	}
	
	private void editCalendarEvent(long calendarEventID){
	    Context context = getContext();
	    Intent intent = new Intent(Intent.ACTION_EDIT);
	    intent.setData(Uri.parse("content://com.android.calendar/events/" + String.valueOf(calendarEventID)));
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
	        | Intent.FLAG_ACTIVITY_SINGLE_TOP
	        | Intent.FLAG_ACTIVITY_CLEAR_TOP
	        | Intent.FLAG_ACTIVITY_NO_HISTORY
	        | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
	   context.startActivity(intent);
	}


}
