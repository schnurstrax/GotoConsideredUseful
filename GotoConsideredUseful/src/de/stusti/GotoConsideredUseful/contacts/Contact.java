package de.stusti.GotoConsideredUseful.contacts;

import java.util.ArrayList;


public class Contact {

	protected String contactId;
	protected String name;
	protected ArrayList<Address> addresses;
	
	
	public void addAddress(Address address) {
		addresses.add(address);
	}
	
	public String getContactId() {
		return contactId;
	}
	public String getName() {
		return name;
	}
	public ArrayList<Address> getAddresses() {
		return addresses;
	}
	
	
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddresses(ArrayList<Address> addresses) {
		this.addresses = addresses;
	}
}
