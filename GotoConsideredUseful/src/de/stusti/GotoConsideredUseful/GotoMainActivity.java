package de.stusti.GotoConsideredUseful;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import de.stusti.GotoConsideredUseful.calendars.Event;
import de.stusti.GotoConsideredUseful.calendars.EventHandler;
import de.stusti.GotoConsideredUseful.contacts.Address;
import de.stusti.GotoConsideredUseful.contacts.ContactHandler;
import de.stusti.GotoConsideredUseful.listview.EntryAdapter;
import de.stusti.GotoConsideredUseful.listview.EntryItem;
import de.stusti.GotoConsideredUseful.listview.Item;
import de.stusti.GotoConsideredUseful.listview.SectionItem;

public class GotoMainActivity extends ListActivity {

	/** Called when the activity is first created. */

	ArrayList<Item> items = new ArrayList<Item>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArrayList<Event> events = getEvents();
		addEventsToItemList(events, items);

		EntryAdapter adapter = new EntryAdapter(this, items);

		setListAdapter(adapter);
	}

	protected ArrayList<Event> getEvents() {

		// Get events from calendar.
		EventHandler eventHandler = new EventHandler(this);
		Calendar calendar = Calendar.getInstance();

		long today = calendar.getTimeInMillis();
		int hours = 48;
		ArrayList<Event> events = eventHandler.getEvents(today, hours);
		// ArrayList<Event> eventsWithLocation =
		// eventHandler.getEventsWithLocation(today, hours);

		// Try to add locations using contact information.
		ContactHandler contactHandler = new ContactHandler(this);
		contactHandler.addLocationsToEvents(events);

		return events;
	}

	protected void addEventsToItemList(ArrayList<Event> events,
			ArrayList<Item> items) {

		for (Event event : events) {
			items.add(new SectionItem(event.getTitle()));

			//ArrayList<String> locationStrings = event.getLocationStrings();
			ArrayList<Address> locationProposals = event.getLocationProposals();
			for (Address address : locationProposals) {
				items.add(new EntryItem(address.getStreet(), address.getPostCodeAndCity()));
			}
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		if (!items.get(position).isSection()) {

			EntryItem item = (EntryItem) items.get(position);

			Toast.makeText(this, "You clicked " + item.title,
					Toast.LENGTH_SHORT).show();

		}

		super.onListItemClick(l, v, position, id);
	}
}
