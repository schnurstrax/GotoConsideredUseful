package de.stusti.GotoConsideredUseful;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class GotoConsideredUsefulActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

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
}