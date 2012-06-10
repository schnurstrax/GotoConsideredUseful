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
        LinearLayout leView = new LinearLayout(mContext);
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
        
        LinearLayout leView3 = new LinearLayout(mContext);
        leView3.setOrientation(LinearLayout.VERTICAL);
        leView3.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        
        ArrayList<Address> alist = input.get(position).getLocationProposals();
        
        
        for(Address a:alist) {
        	LinearLayout leView4 = new LinearLayout(mContext);
            leView4.setOrientation(LinearLayout.HORIZONTAL);
            leView4.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            
            TextView adr = new TextView(mContext);
            ImageView contact = new ImageView(mContext);
            contact.setImageDrawable(mContext.getResources().getDrawable(R.drawable.contactssmall));
            ImageView maps = new ImageView(mContext);
            maps.setImageDrawable(mContext.getResources().getDrawable(R.drawable.maps64));
            	
        	
        }
        
        
        
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}