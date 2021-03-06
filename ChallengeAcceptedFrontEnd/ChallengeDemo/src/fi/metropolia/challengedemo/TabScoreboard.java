/*
 * THE SCOREBOARD SHOWS AN ARRAY SORTED BY POINTS (DESCENDING) OF USERS
 * 
 * IN FUTURE VERSIONS THE IDEA WOULD BE THAT EACH USER IS CLICKABLE AND A MORE DETAILED
 * SCORE IS SHOWN AS SEEN IN THE DESIGN DOCUMENTATION
 * 
 * THE FUNCTIONS WORK IN EXACTLY THE SAME MANNER AS THE START PAGE EXCEPT HERE THE POINTS OF EACH
 * USER IS ALSO SHOWN
 * 
*/

package fi.metropolia.challengedemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by mike on 18/11/2014.
 */
public class TabScoreboard extends Fragment {
	LinearLayout myView;
	TableLayout tableLayout;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
myView = (LinearLayout) inflater.inflate(R.layout.tab_scoreboard, container, false);



readScoresFromDb();


return myView;


}
	
	
	public void readScoresFromDb(){
		tableLayout = (TableLayout) myView.findViewById(R.id.scoreboard);
		
		
	
		
		
		int numRows = ((MainActivity)getActivity()).getScoreboard().size();
		       
	    
        for (int i=0; i< numRows; i++) {
        	Context context = getActivity();
            TableRow row = new TableRow(context);
            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

            row.setLayoutParams(params);
            row.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            //row.setBackgroundColor(getResources().getColor(R.color.actionbar));
            row.setPadding(1, 1, 1, 1);

            // Challenge Name COLUMN
            TextView tv1 = new TextView(context);
            tv1.setTextSize(40);
            tv1.setPadding(25, 0, 25, 0);
            
            // Points COLUMN
            TextView tv2 = new TextView(context);
            tv2.setTextSize(40);
            tv2.setPadding(25, 0, 25, 0);
                 
            


            final String dbUser = ((MainActivity)getActivity()).getScoreboard().get(i).getName();
            tv1.setText(dbUser);
            
            final String points = Integer.toString(((MainActivity)getActivity()).getScoreboard().get(i).getTotalPoints());
          tv2.setText(points);
           
           
            row.addView(tv1);
            row.addView(tv2);

            

            TableRow rowGap = new TableRow(context);

            rowGap.setLayoutParams(params);
            rowGap.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            rowGap.setBackgroundColor(getResources().getColor(R.color.white));
            rowGap.setPadding(1, 1, 1, 1);

            tableLayout.addView(row);
            tableLayout.addView(rowGap);
            
        }






        }
		
		
	}

	
