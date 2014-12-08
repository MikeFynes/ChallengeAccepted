package fi.metropolia.challengedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChallengeActivity extends Activity {
	
	 int uId,challId, challengerId;
	 String challengeName, challengeDesc;
	 
	 TextView title, desc;
	 Button btnChallenge, btnBack;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.activity_challenger);
	        
	        int userSelected, grabChallId, grabChallengerId;
	        String grabChallName, grabChallDesc;
	       
	        if (savedInstanceState == null) {
	            Bundle extras = getIntent().getExtras();
	            if(extras == null) {
	            	Log.d("NO USERS!", "NO USERS SELECTED!");
	            } else {
	            	userSelected = extras.getInt("userID");
	            	grabChallId = extras.getInt("challID");
	            	grabChallName = extras.getString("challName");
	            	grabChallDesc = extras.getString("challDesc");
	            	grabChallengerId = extras.getInt("challengerId");
	            	
	            	
	            	setuId(userSelected);
	            	setChallId(grabChallId);
	            	setChallengeName(grabChallName);
	            	setChallengeDesc(grabChallDesc);
	            	setChallengerId(grabChallengerId);
	            	
	            }
	        } else {
	        	userSelected= (Integer) savedInstanceState.getSerializable("userID");
	        	grabChallId= (Integer) savedInstanceState.getSerializable("challID");
	        	grabChallName= (String) savedInstanceState.getSerializable("challName");
	        	grabChallDesc= (String) savedInstanceState.getSerializable("challDesc");
	        	grabChallengerId= (Integer) savedInstanceState.getSerializable("challengerId");
	        	setuId(userSelected);
            	setChallId(grabChallId);
            	setChallengeName(grabChallName);
            	setChallengeDesc(grabChallDesc);
            	setChallengerId(grabChallengerId);
	        }
	        
	        
	        title = (TextView) findViewById(R.id.chall_title);
	        title.setText(getChallengeName());
	        
	        desc = (TextView) findViewById(R.id.chall_desc);
	        desc.setText(getChallengeDesc());
	        
	        
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
		
	    public void startSecondAct() {
	    	
	        Intent intent = new Intent(this, MainActivity.class);
	        intent.putExtra("userID", getChallengerId());
	        
	       
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
	    
	    
	    
	    
}
