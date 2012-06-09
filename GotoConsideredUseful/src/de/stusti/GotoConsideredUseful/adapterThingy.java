package de.stusti.GotoConsideredUseful;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class adapterThingy extends ArrayAdapter<Event>{

    Context context;
    int layoutResourceId;   
    Event data[] = null;
   
    public adapterThingy(Context context, int layoutResourceId, Event[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;
       
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new WeatherHolder();
            holder.imgIcon = (TextView)row.findViewById(R.id.location);
            holder.txtTitle = (TextView)row.findViewById(R.id.event);
           
            row.setTag(holder);
        }
        else
        {
            holder = (WeatherHolder)row.getTag();
        }
       
        Event weather = data[position];
        holder.txtTitle.setText(weather.getTitle());
        holder.imgIcon.setText(weather.getLocation());
        final View row2 = row;
        row2.setOnClickListener(new View.OnClickListener() {
			
 			public void onClick(View v) {
 				TextView t = (TextView)row2.findViewById(R.id.location);
 				GotoConsideredUsefulActivity goa = (GotoConsideredUsefulActivity)context;
 				goa.callGeo((String) t.getText());
 			}
 		});
        Button maps = (Button)row2.findViewById(R.id.maps);
        maps.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				TextView t = (TextView)row2.findViewById(R.id.location);
				GotoConsideredUsefulActivity goa = (GotoConsideredUsefulActivity)context;
				goa.callMap((String) t.getText());
			}
		});
        Button call = (Button)row2.findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				throw new ArrayIndexOutOfBoundsException();				
			}
		});
        return row2;
    }
   
    static class WeatherHolder
    {
        TextView imgIcon;
        TextView txtTitle;
    }
}