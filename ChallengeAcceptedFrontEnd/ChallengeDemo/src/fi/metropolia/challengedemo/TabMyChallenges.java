/*
 * THIS CLASS ALLOWS THE USER TO MAINTAIN THEIR OWN CHALLENGE STATUS
 * USING A SERIES OF IF STATEMENTS THE FINAL VIEW THAT IS SHOWN IS DEPENDENT ON
 * A VARIETY OF FACTORS
 * 
 * 
 * 
*/
package fi.metropolia.challengedemo;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by mike on 18/11/2014.
 */
public class TabMyChallenges extends Fragment{
    RelativeLayout myView;
    LinearLayout buttonView;
    TextView tv1, tv2, tv_status, tv_points_value;
    int userEntry;
    String method;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        myView = (RelativeLayout) inflater.inflate(R.layout.my_challenges, container, false);
        
        buttonView = (LinearLayout) myView.findViewById(R.id.my_button_view);
        
       ImageView im = (ImageView) myView.findViewById(R.id.Img1);
        tv1 = (TextView) myView.findViewById(R.id.Tv1);
        tv2 = (TextView) myView.findViewById(R.id.Tv2);
        tv_status = (TextView) myView.findViewById(R.id.my_status);
        tv_points_value = (TextView) myView.findViewById(R.id.points_value);
        
        userEntry = ((MainActivity)getActivity()).getuId();
    Boolean challengeActive =((MainActivity)getActivity()).checkIfChallenged(userEntry);
    //    Boolean challengeActive = false;

        if(challengeActive) {
            
         
            String challengeTitle = ((MainActivity)getActivity()).checkChallName(userEntry);
            String challengeDesc = ((MainActivity)getActivity()).checkChallDesc(userEntry);
            int challengePoints = ((MainActivity)getActivity()).checkPoints(userEntry);
            int iconId = ((MainActivity)getActivity()).getIcon(userEntry);
            tv1.setText(challengeTitle);
            tv2.setText(challengeDesc);
            tv_points_value.setText(Integer.toString(challengePoints));
            
            im.setImageResource(iconId);


            Boolean challNotified = ((MainActivity) getActivity()).checkIfNotified(userEntry);
            if (challNotified) {
            	tv_status.setText(getResources().getString(R.string.text_accepted));
            	
                Button btn_complete = new Button(getActivity());
                btn_complete.setText(R.string.btn_complete);
                btn_complete.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                btn_complete.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                    	((MainActivity) getActivity()).completeChallenge(userEntry);
                    	setMethod("challengeComplete");
                    	startSecondAct();
                        
                    }
                });
                buttonView.addView(btn_complete);
            } else {
            	tv_status.setText(getResources().getString(R.string.text_received));
                Button btn_accept = new Button(getActivity());
                btn_accept.setText(R.string.btn_accept);
                btn_accept.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                
                
                btn_accept.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                    	int response = 1;
                    	((MainActivity) getActivity()).challengeResponse(userEntry, response);
                    	setMethod("challengeResponse");
                    	startSecondAct();
                    }
                });
                Button btn_decline = new Button(getActivity());
                btn_decline.setText(R.string.btn_decline);
                btn_decline.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                btn_decline.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                    	int response = 0;
                    	((MainActivity) getActivity()).challengeResponse(userEntry, response);
                    	setMethod("challengeResponse");
                    	startSecondAct();
                    }
                });
                buttonView.addView(btn_accept);
                buttonView.addView(btn_decline);
            }
        }


        else{
            
           im.setImageResource(R.drawable.unhappy);
            tv1.setText(getResources().getString(R.string.no_challTitle));
            tv2.setText(getResources().getString(R.string.no_challDesc));
        }


     
        return myView;
    }



    public void startSecondAct() {
    	
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("userID", userEntry);
     
        intent.putExtra("nav", method);
       
        startActivity(intent);
        getActivity().finish();

    }



	public String getMethod() {
		return method;
	}



	public void setMethod(String method) {
		this.method = method;
	}







}
