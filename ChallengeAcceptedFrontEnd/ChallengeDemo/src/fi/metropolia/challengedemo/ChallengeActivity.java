/*
 * THIS IS THE ACTIVITY THAT RUNS WHEN A CHALLENGE IS SELECTED
 * 
 * IT SHOWS THE CHALLENGE, CATEGORY, AND POINTS WORTH OF THE CHALLENGE 
 * 
 * THE USER ID IS CARRIED OVER FROM THE FRAGMENT IT WAS LANCHED
 *  FROM TO ALLOW A CHALLENGE TO BE SENT IMMEDIATELY
 * 
 * 
*/

package fi.metropolia.challengedemo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChallengeActivity extends Activity {
	
	 int uId,challId, challengerId, catId, points;
	 String uName, challengeName, challengeDesc;
	
	 TextView title, desc, tv_points;
	 Button btnChallenge, btnBack;
	 
	    TypedArray ta;
	    
	    
	    
	 int numIcons;
	 int[] iconArray;
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.activity_challenger);
	        
	        int userSelected, grabChallId, grabChallengerId, grabCatId, grabPoints;
	        String grabUserName, grabChallName, grabChallDesc;
	       
	        /*
	         * THE BELOW GRABS ALL EXTRA ITEMS ADDED TO THE INTENT AND ALLOWS PRIMITIVES 
	         * TO BE CARRIED FROM ACTIVITY TO ACTIVITY EASILY
	         * 
	         * 
	        */
	        if (savedInstanceState == null) {
	            Bundle extras = getIntent().getExtras();
	            if(extras == null) {
	            	Log.d("NO USERS!", "NO USERS SELECTED!");
	            } else {
	            	userSelected = extras.getInt("userID");
	            	grabChallId = extras.getInt("challID");
	            	grabChallName = extras.getString("challName");
	            	grabChallDesc = extras.getString("challDesc");
	            	grabUserName = extras.getString("name");
	            	grabChallengerId = extras.getInt("challengerId");
	            	grabCatId = extras.getInt("catId");
	            	grabPoints = extras.getInt("points");
	            	
	            	setuId(userSelected);
	            	setChallId(grabChallId);
	            	setChallengeName(grabChallName);
	            	setChallengeDesc(grabChallDesc);
	            	setuName(grabUserName);
	            	setChallengerId(grabChallengerId);
	            	setCatId(grabCatId);
	            	setPoints(grabPoints);
	            	
	            }
	        } else {
	        	userSelected= (Integer) savedInstanceState.getSerializable("userID");
	        	grabChallId= (Integer) savedInstanceState.getSerializable("challID");
	        	grabCatId= (Integer) savedInstanceState.getSerializable("catId");
	        	grabPoints= (Integer) savedInstanceState.getSerializable("points");
	        	grabChallName= (String) savedInstanceState.getSerializable("challName");
	        	grabChallDesc= (String) savedInstanceState.getSerializable("challDesc");
	        	grabChallengerId= (Integer) savedInstanceState.getSerializable("challengerId");
	        	grabUserName= (String) savedInstanceState.getSerializable("name");
	        	
	        	
	        	setuId(userSelected);
            	setChallId(grabChallId);
            	setChallengeName(grabChallName);
            	setChallengeDesc(grabChallDesc);
            	setChallengerId(grabChallengerId);
            	setuName(grabUserName);
            	setCatId(grabCatId);
            	setPoints(grabPoints);
	        }
		       ta = getResources().obtainTypedArray(R.array.icons);
		       numIcons = ta.length();
		       
		       
		      iconArray = new int[numIcons];
		      grabIcons();
	        
	        
	        
	        int iconPath = iconArray[getCatId()];
	        
	        
	        ImageView im = (ImageView) findViewById(R.id.catIm);
	        im.setImageResource(iconPath);
	        
	        
	        title = (TextView) findViewById(R.id.chall_title);
	        title.setText(getChallengeName());
	        
	        desc = (TextView) findViewById(R.id.chall_desc);
	        desc.setText(getChallengeDesc());
	        
	        tv_points = (TextView) findViewById(R.id.points_value);
	        tv_points.setText(Integer.toString(getPoints()));
	        
	        btnChallenge = (Button) findViewById(R.id.btnChallenge);
	        btnChallenge.setOnClickListener(new View.OnClickListener() {
	                  @Override
	                public void onClick(View v) {

	                			sendChallenge(getuId(), getChallId());
	                			startSecondAct();
	                			finish();
	                	  
	                
	                    
	                } }
	            )
	            ;
	        
	        btnBack = (Button) findViewById(R.id.btnBack);
	        btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
              public void onClick(View v) {

                	startSecondAct();
              			finish();
              	  
              
                  
              } }
          )
          ;
	        
	
}
	    
		public void sendChallenge(int userEntry, int challengeId){
			
			new AsyncChallengeUser().execute(userEntry, challengeId);
			
		}
		
		
		/*
		 * ONCE CHALLENGE IS SENT THIS RUNS TO BRING USER BACK TO MAIN ACTIVITY
		 * THE WELCOME SCREEN DIFFERS DEPENDING ON THE ACTIVITY YOU ARRIVED FROM
		 * 
		*/
		
	    public void startSecondAct() {
	    	
	        Intent intent = new Intent(this, MainActivity.class);
	        intent.putExtra("userID", getChallengerId());
	        intent.putExtra("nav", "challengeSent");
	        
	       
	        startActivity(intent);

	    }
	    
	    
	    
		public int getChallId() {
			return challId;
		}
		public void setChallId(int challId) {
			this.challId = challId;
		}
		public String getChallengeName() {
			return challengeName;
		}
		public void setChallengeName(String challengeName) {
			this.challengeName = challengeName;
		}
		public String getChallengeDesc() {
			return challengeDesc;
		}
		public void setChallengeDesc(String challengeDesc) {
			this.challengeDesc = challengeDesc;
		}
		public int getuId() {
			return uId;
		}
		public void setuId(int uId) {
			this.uId = uId;
		}

		public int getChallengerId() {
			return challengerId;
		}

		public void setChallengerId(int challengerId) {
			this.challengerId = challengerId;
		}

		public String getuName() {
			return uName;
		}

		public void setuName(String uName) {
			this.uName = uName;
		}
		
		
		
		
		
		
		
		public int getCatId() {
			return catId;
		}

		public void setCatId(int catId) {
			this.catId = catId;
		}
		
		

		public int getPoints() {
			return points;
		}

		public void setPoints(int points) {
			this.points = points;
		}


	    
public void grabIcons(){
	
  
    // INNER LOOP GRABS ICONS NEEDED
    for (int j = 0; j < numIcons; j++){
    	 iconArray[j] = ta.getResourceId(j, 0);
        
    }
		
}	    
	    

	    
}

		
		

