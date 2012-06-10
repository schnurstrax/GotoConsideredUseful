package de.stusti.GotoConsideredUseful;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.stusti.GotoConsideredUseful.calendars.*;
import de.stusti.GotoConsideredUseful.location.Address;

public class newAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Event> input;
    
    public newAdapter(Context c, ArrayList<Event> inp) {
        mContext = c;
        input = inp;
    }

    public int getCount() {
        return input.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout leView;    
        LinearLayout leView3 = new LinearLayout(mContext);
        leView3.setOrientation(LinearLayout.VERTICAL);
        leView3.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    
        
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        	leView = new LinearLayout(mContext);
            leView.setOrientation(LinearLayout.VERTICAL);
            leView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            
            LinearLayout leView2 = new LinearLayout(mContext);
            leView2.setOrientation(LinearLayout.HORIZONTAL);
            leView2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            
            TextView teView = new TextView(mContext);
            TextView timeView = new TextView(mContext);
            ImageView call = new ImageView(mContext);
            call.setImageDrawable(mContext.getResources().getDrawable(R.drawable.call64));
            
            leView.addView(leView2);
            
           } else {
            leView = (LinearLayout) convertView;
        }
        ArrayList<Address> alist = input.get(position).getLocationProposals();
        for(final Address a:alist) {
        	LinearLayout leView4 = new LinearLayout(mContext);
            leView4.setOrientation(LinearLayout.HORIZONTAL);
            leView4.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            
            TextView adr = new TextView(mContext);
            ImageView contact = new ImageView(mContext);
            contact.setImageDrawable(mContext.getResources().getDrawable(R.drawable.contactssmall));
            ImageView maps = new ImageView(mContext);
            maps.setImageDrawable(mContext.getResources().getDrawable(R.drawable.maps64));
            
            adr.setText(a.getAddress());
            maps.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					GotoConsideredUsefulActivity goa = (GotoConsideredUsefulActivity)mContext;
					goa.callMap(a.getAddress());
				}
			});
            leView4.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					GotoConsideredUsefulActivity goa = (GotoConsideredUsefulActivity)mContext;
					goa.callGeo(a.getAddress());
				}
			});
            leView3.addView(leView4);
        }
        leView.addView(leView3);
        return leView;
    }
}