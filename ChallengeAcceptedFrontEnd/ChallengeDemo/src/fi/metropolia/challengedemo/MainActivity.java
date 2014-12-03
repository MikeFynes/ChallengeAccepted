package fi.metropolia.challengedemo;


import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by mike on 18/11/2014.
 */
public class MainActivity extends Activity implements AsyncResponse {
	 
	   AsyncGetChallenges asyncChallengeGrab = new AsyncGetChallenges();
	    List<Challenges> challengesList;
	    
	    AsyncGetUsers asyncUserGrab  =new AsyncGetUsers();
	    List<Users> usersList;
	    
	    
	    
	    String category;
	    
	 int uId, challId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        int userSelected;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
            	Log.d("NO USERS!", "NO USERS SELECTED!");
            } else {
            	userSelected = extras.getInt("userID");
            	setuId(userSelected);
            }
        } else {
        	userSelected= (Integer) savedInstanceState.getSerializable("userID");
        	setuId(userSelected);
        }
        
        
        
        asyncChallengeGrab.listener = this;
        asyncChallengeGrab.execute("allChallenges");
        challengesList = new ArrayList<Challenges>();
        

      
       asyncUserGrab.listener = this;
          asyncUserGrab.execute("allUsers");
       usersList = new ArrayList<Users>();
       
       

       
       ActionBar actionBar = getActionBar();
  
       String label1 = getResources().getString(R.string.tab_lbl_welcome);

       Fragment TF_welcome = new TabWelcome();
       Tab welcomeTab = actionBar.newTab();
       welcomeTab.setText(label1);
       TListener t_welc = new TListener(TF_welcome);
       welcomeTab.setTabListener(t_welc);
       actionBar.addTab(welcomeTab);       
       
 
        label1 = getResources().getString(R.string.tab_lbl_scoreboard);

        Fragment TF_score = new TabScoreboard();
        Tab scoreBoardTab = actionBar.newTab();
        scoreBoardTab.setText(label1);
        TListener t2 = new TListener(TF_score);
        scoreBoardTab.setTabListener(t2);
        actionBar.addTab(scoreBoardTab);
        

        label1 = getResources().getString(R.string.tab_lbl_my_challenges);

        Fragment TF_myC = new TabMyChallenges();
        Tab myChallengeTab = actionBar.newTab();
        myChallengeTab.setText(label1);
        TListener t1 = new TListener(TF_myC);
        myChallengeTab.setTabListener(t1);
        actionBar.addTab(myChallengeTab);




        label1 = getResources().getString(R.string.tab_lbl_create_challenge);

        Fragment TF_allChall = new TabChallenge();
        Tab challengeTab = actionBar.newTab();
        challengeTab.setText(label1);
        TListener t3 = new TListener(TF_allChall);
        challengeTab.setTabListener(t3);
        actionBar.addTab(challengeTab);

        
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
     
        
        
        
        
    }
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	
    public Boolean checkIfChallenged(int userEntry){
    	Boolean isActive = usersList.get(userEntry).isChallengeActive();
        return isActive;
    }
    public Boolean checkIfNotified(int userEntry){
    	Boolean isNotified = usersList.get(userEntry).isNotified();
        return isNotified;
    }

    public String checkChallName(int userEntry){
    	int challengeId =  usersList.get(userEntry).getCurrentChallenge();
    	String challengeName = challengesList.get(challengeId-1).getName();
        return challengeName;
    }
    public String checkChallDesc(int userEntry){
    	int challengeId =  usersList.get(userEntry).getCurrentChallenge();
    	String challengeDesc = challengesList.get(challengeId-1).getDescription();
        return challengeDesc;
    }
    
    public void completeChallenge(int userEntry){
    	int userToSend = usersList.get(userEntry).getId();
    	new AsyncCompleteChallenge().execute(userToSend);
    
    	
    }
    
    public void challengeResponse(int userEntry, int response){
    	int userToSend = usersList.get(userEntry).getId();
    	new AsyncChallengeResponse().execute(userToSend, response);
    	
    	
    }
    
    
	public void sendChallenge(int userEntry, int challengeId){
		
		new AsyncChallengeUser().execute(userEntry, challengeId);
		
	}
	
	

	
	
	
	
	
	
	
	public int getChallId() {
		return challId;
	}
	public void setChallId(int challId) {
		this.challId = challId;
	}
	public List<Challenges> getChallengesList() {
		return challengesList;
	}
	@Override
	public void challengeGrabber(List<Challenges> output) {
		
		challengesList = output;
		
	}
	@Override
	public void userGrabber(List<Users> output) {
		// TODO Auto-generated method stub
		Log.d("USERGRAB", "START");
		usersList = output;
		Log.d("USERGRAB", "COMPLETE");
	}
	
	  public List<Users> getUsersList(){
	    	
	    	return usersList;
	    }
	@Override
	public void loadComplete() {
		// TODO Auto-generated method stub
		
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	

	    
    

}

