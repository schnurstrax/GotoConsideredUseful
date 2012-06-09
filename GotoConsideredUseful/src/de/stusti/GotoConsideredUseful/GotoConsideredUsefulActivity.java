package de.stusti.GotoConsideredUseful;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class GotoConsideredUsefulActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

		EventHandler eventHandler = new EventHandler(this);
		Date today = new Date();
		int hours = 6;
		ArrayList<Event> events = eventHandler.getEvents(today, hours);
		 
		//ContactHandler contactHandler = new ContactHandler(this);
		
		TextView tv = new TextView(this);
		String titel = "";
		
		for(int i = 0; i<events.size(); i++){
			titel += events.get(i).getTitle();
		}
		tv.setText("Titel: " + titel);
			
		setContentView(tv);
	  // LIST ADAPTER WITH CURSOR
      Cursor cursor = getContactCursor();
      
      String[] fields = new String[] {
              ContactsContract.Data.DISPLAY_NAME, 
              ContactsContract.Data.DISPLAY_NAME
      };
      
      ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.termin_listitem, SOURCE);
      
      

	  ListView lv = new ListView(this);
	  lv.setAdapter(listAdapter);
	  lv.setTextFilterEnabled(true);
	  setContentView(lv);
	  lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	      // When clicked, show a toast with the TextView text
	      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
	          Toast.LENGTH_SHORT).show();
	      callGeo("Berlin Hbf");
	    }
	  });
	}

	protected Cursor getContactCursor() {		
		 // Run query
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME, 
                ContactsContract.Contacts.DISPLAY_NAME
        };
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '" + "1" + "'";
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
	}
	
	protected void callGeo(String destination) {
    	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + destination));
        startActivity(i);
    }
	
	protected void callMap(String destination) {
		Intent intent = new Intent(this, MapViewActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("marker", destination);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}