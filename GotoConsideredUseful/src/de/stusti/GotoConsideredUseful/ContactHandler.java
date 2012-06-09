package de.stusti.GotoConsideredUseful;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class ContactHandler extends Activity {

//	public ArrayList<Event> getContacts() {
//		ArrayList<Event> contacts = new ArrayList<Event>();
//		return contacts;
//	}
	
	// LIST ADAPTER WITH CURSOR
			//Cursor cursor = getContactCursor();
			//String[] fields = getContactFields();	
	
	

//  ListAdapter listAdapter = new SimpleCursorAdapter(this,
//          // Use a template that displays a text view
//          android.R.layout.simple_list_item_1,
//          // Give the cursor to the list adapter
//          cursor,
//          // Map the DISPLAY_NAME column to...
//          fields,
//          // The "text1" view defined in the XML template
//          new int[] {android.R.id.text1});
//  
//  setListAdapter(listAdapter);

  //ListView lv = getListView();
  //lv.setTextFilterEnabled(true);

//  lv.setOnItemClickListener(new OnItemClickListener() {
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//      // When clicked, show a toast with the TextView text
//      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
//          Toast.LENGTH_SHORT).show();
//      callGeo("Berlin Hbf");
//    }
//  });
	
	protected Cursor getCursor() {		
        Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;       
        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };       
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '" + "1" + "'";       
        String[] selectionArgs = null;      
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        Cursor managedCursor = managedQuery(contactsUri, projection, selection, selectionArgs, sortOrder);
        return managedCursor;
	}

	
	protected String[] getFields() {
	    String[] fields = new String[] {
	    		ContactsContract.Data.DISPLAY_NAME
	    };
	    return fields;
	}
}
