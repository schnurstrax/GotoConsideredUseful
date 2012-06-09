package de.stusti.GotoConsideredUseful;

import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.*;

public class GotoConsideredUsefulActivity extends FragmentActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

		setContentView(R.layout.hauptteil);
		EventHandler eventHandler = new EventHandler(this);
		Date today = new Date();
		ArrayList<Event> events = eventHandler.getEvents(today);
		frag f;
		FragmentTransaction fg = getSupportFragmentManager().beginTransaction();
		for (Event e:events) {
		f = new frag(e.getTitle(), e.getDescription());
		fg.add(R.id.linearStuff, f);
		}
		fg.commit();
		

	}
	
	protected void callGeo(String destination) {
    	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + destination));
        startActivity(i);
    }
}