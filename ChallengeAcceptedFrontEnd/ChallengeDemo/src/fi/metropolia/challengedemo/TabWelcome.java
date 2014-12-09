/*
 * THE WELCOME TAB WELCOMES THE USER TO THE APPLICATION AND AS A DESIGN FEATURE IS EXTREMELY
 * SIMPLE AND WELCOMING, THIS PAGE WILL BE REPLACED WITH THE NEWSFEED IN FUTURE DEVELOPMENT
 * 
 * AS IT IS DIRECTLY GRABBING DATA FROM AN ACTIVITY THAT MAY NOT BE FULLY CREATED YET
 * A WHILE LOOP IS SETUP TO CHECK THAT THE REQUIRED VARIABLE IS NOT NULL
 * THIS SHOULD NOT BE A PROBLEM ON A REAL DEVICE ONLY ON A SLOW EMULATOR
 * 
 * 
*/

package fi.metropolia.challengedemo;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabWelcome extends Fragment{
    LinearLayout myView;
    TextView tv_from, tv_title;
    String from;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        myView = (LinearLayout) inflater.inflate(R.layout.tab_welcome, container, false);
     
        tv_title = (TextView) myView.findViewById(R.id.welcome_title);
        tv_from = (TextView) myView.findViewById(R.id.welcome_from);
        
        setFrom(((MainActivity)getActivity()).getNavFrom());
        
        setText();
        return myView;
    }
    
    
    public void setText(){
    	while(from == null){
    		
    	
    		try {
    			
				Thread.sleep(1000);
				Log.d("WELCOME TAB", "WAITING FOR MAIN ACTIVITY");
				setFrom(((MainActivity)getActivity()).getNavFrom());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				

    		
    	}
    	
    	
    	 tv_title = (TextView) myView.findViewById(R.id.welcome_title);
         tv_from = (TextView) myView.findViewById(R.id.welcome_from);
    	
    	   if(from.contentEquals("start")){
           	tv_title.setText(getResources().getString(R.string.welcome_start));
           	tv_from.setText(getResources().getString(R.string.welcome_from_start));
           }
           else if(from.contentEquals("challengeResponse")){
           	tv_title.setText(getResources().getString(R.string.welcome_response));
           	tv_from.setText(getResources().getString(R.string.welcome_from_response));
           }
           else if(from.contentEquals("challengeComplete")){
           	tv_title.setText(getResources().getString(R.string.welcome_complete));
           	tv_from.setText(getResources().getString(R.string.welcome_from_complete));
           }
           else if(from.contentEquals("challengeSent")){
           	tv_title.setText(getResources().getString(R.string.welcome_sent));
           	tv_from.setText(getResources().getString(R.string.welcome_from_sent));
           }
           else{
           	tv_title.setText("I HAVE NO IDEA WHERE YOU CAME FROM");
           }
    	   
    
    }


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}

    
    
}
