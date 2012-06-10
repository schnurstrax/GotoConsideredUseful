package de.stusti.GotoConsideredUseful.contacts;

import java.util.ArrayList;

import de.stusti.GotoConsideredUseful.calendars.Event;
import de.stusti.GotoConsideredUseful.location.Address;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

public class ContactHandler {

	static final String DATASOURCE = "CONTACTS";
	protected Activity activity;
	
	public ContactHandler(Activity activity) {
		this.activity = activity;
	}
	
	public void addLocationsToEvents(ArrayList<Event> events) {		
		for(Event event: events) {
			String[] titleWords = event.getTitle().split(" ");
			ArrayList<Address> locationProposals = getLocationProposals(titleWords);
			event.addLocationProposals(locationProposals);
		}
	}
	
	protected ArrayList<Address> getLocationProposals(String[] titleWords) {
		ArrayList<Address> proposals = new ArrayList<Address>();	
		
		for (int i = 0; i < titleWords.length; i++) {
			proposals = getAddressesFromContactsWithKeywordInTitle(titleWords[i]);				
		}		
		return proposals;
	}
	
	protected ArrayList<Address> getAddressesFromContactsWithKeywordInTitle(String keyword) {
		ArrayList<Address> addressProposals = new ArrayList<Address>();
		
		ContentResolver contentResolver = this.activity.getContentResolver();
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC"; 	
		String selection = ContactsContract.Contacts.DISPLAY_NAME + " LIKE '%"+keyword+"%'"; 
 	   	String[] selectionArgs = new String[]{}; 	   
 	   	Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, selection, selectionArgs, sortOrder);
		
 	   if (cursor.getCount() > 0) {       	
           while (cursor.moveToNext()) {
           	
               String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
               //String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
               
               // Get addresses.
               String addressSelection = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
               String[] addressSelectionArgs = new String[]{contactId, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};
               Cursor addressCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, addressSelection,  addressSelectionArgs, null);
               
               if (addressCursor.getCount() > 0) {
            	   ArrayList<Address>  addresses = getAddressesFromCurrentContact(addressCursor);
            	   addressProposals.addAll(addresses);
               }
               addressCursor.close();              
           }
		}		
		return addressProposals;		
	}

	protected ArrayList<Address> getAddressesFromCurrentContact(Cursor addressCursor) {		
		ArrayList<Address> addresses = new ArrayList<Address>();
		
		while(addressCursor.moveToNext()) {			
			Address address = new Address();
			          
			String street = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
			String postalCode = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
			String city = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
			String state = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));                  
			String country = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));            
			String postBox = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
			String type = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));
			 
			address.setStreet(street);
			address.setPostalCode(postalCode);
			address.setCity(city);
			address.setState(state);
			address.setCountry(country);
			address.setPostBox(postBox);
			address.setType(type);			
			address.setDataSource(DATASOURCE);
			 
			addresses.add(address);
         }
		
		return addresses;
	}
}
