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
import de.stusti.GotoConsideredUseful.contacts.Address;

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
    	ArrayList<String> alist = input.get(position).getLocationStrings();
        Event e = input.get(position);
        
        LinearLayout leView;    
        leView = new LinearLayout(mContext);
        LinearLayout leView3 = new LinearLayout(mContext);
        leView3.setOrientation(LinearLayout.VERTICAL);
        leView3.setLayoutParams(new GridView.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        
        leView.setOrientation(LinearLayout.VERTICAL);
        leView.setLayoutParams(new GridView.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        
        LinearLayout leView2 = new LinearLayout(mContext);
        leView2.setOrientation(LinearLayout.HORIZONTAL);
        leView2.setLayoutParams(new GridView.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        
        TextView teView = new TextView(mContext);
        TextView timeView = new TextView(mContext);
        teView.setText(e.getTitle());
        timeView.setText(" - um 00:01   ");
        ImageView call = new ImageView(mContext);
        call.setImageDrawable(mContext.getResources().getDrawable(R.drawable.call64));
        leView2.addView(teView);
        leView2.addView(timeView);
        
        leView2.addView(call);
        
        
        leView.addView(leView2);
        LinearLayout leView4;
        TextView adr;
        ImageView contact;
        ImageView maps;
        for(String a:alist) {
        	leView4 = new LinearLayout(mContext);
            leView4.setOrientation(LinearLayout.HORIZONTAL);
            leView4.setLayoutParams(new GridView.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            
            adr = new TextView(mContext);
            contact = new ImageView(mContext);
            contact.setImageDrawable(mContext.getResources().getDrawable(R.drawable.contactssmall));
            maps = new ImageView(mContext);
            maps.setImageDrawable(mContext.getResources().getDrawable(R.drawable.maps64));
            final String adrs= a;
            adr.setText((CharSequence) adrs);
            maps.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					GotoConsideredUsefulActivity goa = (GotoConsideredUsefulActivity)mContext;
					goa.callMap(adrs);
				}
			});
            leView4.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					GotoConsideredUsefulActivity goa = (GotoConsideredUsefulActivity)mContext;
					goa.callGeo(adrs);
				}
			});
            leView3.addView(leView4);
        }
        leView.addView(leView3);
        return leView;
    }
}