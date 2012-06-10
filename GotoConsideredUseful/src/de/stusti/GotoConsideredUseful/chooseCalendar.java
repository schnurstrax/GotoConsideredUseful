package de.stusti.GotoConsideredUseful;  
      
    import java.util.ArrayList;  
    import java.util.Arrays;  
    import java.util.List;  
      
    import android.app.Activity;  
    import android.content.Context;  
    import android.os.Bundle;  
    import android.view.LayoutInflater;  
    import android.view.View;  
    import android.view.ViewGroup;  
    import android.widget.AdapterView;  
    import android.widget.ArrayAdapter;  
import android.widget.Button;
    import android.widget.CheckBox;  
    import android.widget.ListView;  
import android.widget.TextView;  
      
    public class chooseCalendar extends Activity {  
        
      private ListView mainListView ;  
      private Planet[] planets ;  
      private ArrayAdapter<Planet> listAdapter ;  
      private String[] inp;
      private boolean[] num;
      
      //** Called when the activity is first created.
      @Override  
      public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        Bundle extras = getIntent().getExtras();
        inp = extras.getStringArray("calendars");
        num = new boolean[inp.length];
        for (int i = 0; i <= inp.length; i++) {
        	num[i] = !(inp[i].equals(""));
        }
        
        setContentView(R.layout.main);  
          
        // Find the ListView resource.   
        Button b = (Button) findViewById(R.id.save);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				int resultThingy = Integer.parseInt("1", 2);
				for (int i = 0; i <= num.length; i++) {
					resultThingy = resultThingy*2;
					if (num[i]) resultThingy++;
				}
				resultThingy = Integer.parseInt(new Integer(resultThingy).toString(), 10);
				setResult(resultThingy);
			}
		});
        
        mainListView = (ListView) findViewById( R.id.mainListView );  
          
        // When item is tapped, toggle checked properties of CheckBox and Planet.  
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
          public void onItemClick( AdapterView<?> parent, View item,   
                                   int position, long id) {  
            Planet planet = listAdapter.getItem( position );  
            planet.toggleChecked();  
            if (num[position]) num[position] = false;
            else	num[position] = true;
            PlanetViewHolder viewHolder = (PlanetViewHolder) item.getTag();  
            viewHolder.getCheckBox().setChecked( planet.isChecked() );  
          }  
        });  
      
          
        // Create and populate planets.  
        planets = (Planet[]) getLastNonConfigurationInstance() ;  
        if ( planets == null ) {  
            planets = new Planet[inp.length];
            for (int i = 0; i <= inp.length; i++) {
            	planets[i] = new Planet(inp[i]);
            }
          }  
        ArrayList<Planet> planetList = new ArrayList<Planet>();  
        planetList.addAll( Arrays.asList(planets) );  
          
        // Set our custom array adapter as the ListView's adapter.  
        listAdapter = new PlanetArrayAdapter(this, planetList);  
        mainListView.setAdapter( listAdapter );        
      }  
        
      //** Holds planet data. /  
      private static class Planet {  
        private String name = "" ;  
        private boolean checked = false ;  
        public Planet() {}  
        public Planet( String name ) {  
          this.name = name ;  
        }  
        public Planet( String name, boolean checked ) {  
          this.name = name ;  
          this.checked = checked ;  
        }  
        public String getName() {  
          return name;  
        }  
        public void setName(String name) {  
          this.name = name;  
        }  
        public boolean isChecked() {  
          return checked;  
        }  
        public void setChecked(boolean checked) {  
          this.checked = checked;  
        }  
        public String toString() {  
          return name ;   
        }  
        public void toggleChecked() {  
          checked = !checked ;  
        }  
      }  
        
      //** Holds child views for one row. /  
      private static class PlanetViewHolder {  
        private CheckBox checkBox ;  
        private TextView textView ;  
        public PlanetViewHolder() {}  
        public PlanetViewHolder( TextView textView, CheckBox checkBox ) {  
          this.checkBox = checkBox ;  
          this.textView = textView ;  
        }  
        public CheckBox getCheckBox() {  
          return checkBox;  
        }  
        public void setCheckBox(CheckBox checkBox) {  
          this.checkBox = checkBox;  
        }  
        public TextView getTextView() {  
          return textView;  
        }  
        public void setTextView(TextView textView) {  
          this.textView = textView;  
        }      
      }  
        
      //** Custom adapter for displaying an array of Planet objects. *
      private static class PlanetArrayAdapter extends ArrayAdapter<Planet> {  
          
        private LayoutInflater inflater;  
          
        public PlanetArrayAdapter( Context context, List<Planet> planetList ) {  
          super( context, R.layout.simplerow, R.id.rowTextView, planetList );  
          // Cache the LayoutInflate to avoid asking for a new one each time.  
          inflater = LayoutInflater.from(context) ;  
        }  
      
        @Override  
        public View getView(int position, View convertView, ViewGroup parent) {  
          // Planet to display  
          Planet planet = (Planet) this.getItem( position );   
      
          // The child views in each row.  
          CheckBox checkBox ;   
          TextView textView ;   
            
          // Create a new row view  
          if ( convertView == null ) {  
            convertView = inflater.inflate(R.layout.simplerow, null);  
              
            // Find the child views.  
            textView = (TextView) convertView.findViewById( R.id.rowTextView );  
            checkBox = (CheckBox) convertView.findViewById( R.id.CheckBox01 );  
              
            // Optimization: Tag the row with it's child views, so we don't have to   
            // call findViewById() later when we reuse the row.  
            convertView.setTag( new PlanetViewHolder(textView,checkBox) );  
      
            // If CheckBox is toggled, update the planet it is tagged with.  
            checkBox.setOnClickListener( new View.OnClickListener() {  
              public void onClick(View v) {  
                CheckBox cb = (CheckBox) v ;  
                Planet planet = (Planet) cb.getTag();  
                planet.setChecked( cb.isChecked() );  
              }  
            });          
          }  
          // Reuse existing row view  
          else {  
            // Because we use a ViewHolder, we avoid having to call findViewById().  
            PlanetViewHolder viewHolder = (PlanetViewHolder) convertView.getTag();  
            checkBox = viewHolder.getCheckBox() ;  
            textView = viewHolder.getTextView() ;  
          }  
      
          // Tag the CheckBox with the Planet it is displaying, so that we can  
          // access the planet in onClick() when the CheckBox is toggled.  
          checkBox.setTag( planet );   
            
          // Display planet data  
          checkBox.setChecked( planet.isChecked() );  
          textView.setText( planet.getName() );        
            
          return convertView;  
        }  
          
      }  
        
      public Object onRetainNonConfigurationInstance() {  
        return planets ;  
      }  
    }