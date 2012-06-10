package de.stusti.GotoConsideredUseful;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class ContactHandler {

	protected Activity activity;
	
	public ContactHandler(Activity activity) {
		this.activity = activity;
	}
	
	public void addLocationsToEvents(ArrayList<Event> events) {
		
		//ArrayList<Contact> contacts = getContactsWithAddresses();
		
		//Log.v("CONTACT__XXX1", ""+contacts.size());
		//Log.v("CONTACT__XXX2", ""+contacts.get(1).getName());
		
		for(Event event: events) {
			//if ((null == event.getLocation()) || ("" == event.getLocation())) {
				String[] titleWords = event.getTitle().split(" ");
				ArrayList<Address> locationProposals = getLocationProposals(titleWords);
				event.setLocationProposals(locationProposals);
				
				
				if (locationProposals.size() > 0) {
					String name = event.getTitle();
					event.setLocation(locationProposals.get(0).getStreet() +"  " + locationProposals.get(0).getPostalCode() + " " +locationProposals.get(0).getCity());
					//event.setTitle(name + locationProposals.get(0).getStreet());
				}
			//}
		}
	}
	
	protected ArrayList<Address> getLocationProposals(String[] titleWords) {
		ArrayList<Address> proposals = new ArrayList<Address>();
				
		for(int i = 0; i < titleWords.length; i++) {
			proposals = getAddressesFromContactsWithKeywordInTitle(titleWords[i]);				
		}
		
		return proposals;
	}
	
	protected ArrayList<Address> getAddressesFromContactsWithKeywordInTitle(String keyword) {
		ArrayList<Address> addressProposals = new ArrayList<Address>();
		
		ContentResolver contentResolver = this.activity.getContentResolver();
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC"; 
		//String[] projectionFields = new String[] {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};
		
		String selection = ContactsContract.Contacts.DISPLAY_NAME + " LIKE '%"+keyword+"%'"; 
 	   	String[] selectionArgs = new String[]{}; 
 	   
 	   	Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, selection, selectionArgs, sortOrder);
		
 	   if (cursor.getCount() > 0) {       	
           while (cursor.moveToNext()) {
           	
               String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
               //String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
               
               // Get addresses.
               String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
               String[] addrWhereParams = new String[]{contactId, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};

               Cursor addressCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                      null, 
                      addrWhere, 
                      addrWhereParams, 
                      null);
               
               if (addressCursor.getCount() > 0) {
            	   ArrayList<Address>  addresses = getAddressesFromCurrentContact(addressCursor);
            	   addressProposals.addAll(addresses);
               }
               addressCursor.close();              
           }
		}
		
		return addressProposals;		
	}
	
//	public ArrayList<Contact> getContactsWithAddresses() {		
//		ArrayList<Contact> contacts = new ArrayList<Contact>();
//		
//		ContentResolver contentResolver = this.activity.getContentResolver();
//		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC"; 
//		//String[] projectionFields = new String[] {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};
//		
//		String selection = ContactsContract.Data.MIMETYPE + " = ?"; 
// 	   	String[] selectionArgs = new String[]{ContactsContract.CommonDataKinds.StructuredPostal.STREET}; 
// 	   
// 	   Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, selection, selectionArgs, sortOrder);
//
//		if (cursor.getCount() > 0) {       	
//            while (cursor.moveToNext()) {
//            	
//                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//              
////                private String getNote(long contactId) { 
////                	   String note = null; 
////                	   String[] columns = new String[] { ContactsContract.CommonDataKinds.StructuredPostal.STREET}; 
////                	   String where = ContactsContract.Data.RAW_CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"; 
////                	   String[] whereParameters = new String[]{Long.toString(contactId), ContactsContract.CommonDataKinds.StructuredPostal.STREET}; 
////                	   Cursor contacts = getContentResolver().query(ContactsContract.Data.CONTENT_URI, projection, where, whereParameters, null); 
////                	   if (contacts.moveToFirst()) { 
////                	      rv = contacts.getString(0); 
////                	   } 
////                	   contacts.close(); 
////                	   return note; 
////                	} 
////                
//                
//                // Get addresses.
//                String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
//                String[] addrWhereParams = new String[]{contactId, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};
//
//                Cursor addressCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
//                       null, 
//                       addrWhere, 
//                       addrWhereParams, 
//                       null);
//                
//                if (addressCursor.getCount() > 0) {
//                	ArrayList<Address>  addresses = getAddressesFromCurrentContact(addressCursor);
//
//                    Contact contact = new Contact();
//                 	contact.setContactId(contactId);
//                 	contact.setName(contactName);
//                 	contact.setAddresses(addresses);
//                 	
//                 	contacts.add(contact);
//                }
//                addressCursor.close();
//               
//            }
//		}
//		return contacts;
//    }

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
			 
			addresses.add(address);
         }
		
		return addresses;
	}

	protected Cursor getCursor() {	
		
        Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;  
        
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '" + "1" + "'";       
        String[] selectionArgs = null;      
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        Cursor managedCursor = this.activity.managedQuery(
        		contactsUri, 
        		getProjection(), 
        		selection, 
        		selectionArgs, 
        		sortOrder);
        
        return managedCursor;
	}

	
	protected String[] getFields() {
	    String[] fields = new String[] {
	    		ContactsContract.Data.DISPLAY_NAME
	    };
	    return fields;
	}

	protected String[] getProjection() {
		String[] projectionArgs = new String[] {
			ContactsContract.Contacts._ID,
	    	ContactsContract.Contacts.DISPLAY_NAME,	
	    	ContactsContract.Contacts.Data.RAW_CONTACT_ID
		};
		return projectionArgs;
	}
	
}
