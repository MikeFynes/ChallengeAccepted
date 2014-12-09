/*
 * THIS INTERFACE ALLOWS THE ASYNCTASKS THAT RETRIEVE INFORMATION FROM THE DATABASE
 * 
 * TO INTERACT WITH THE ACTIVITY CLASSES
 * 
 * 
 * 
 * 
*/


package fi.metropolia.challengedemo;

import java.util.List;

/**
 * Created by mike on 18/11/2014.
 */
public interface AsyncResponse {
    // GRABBING USERS
	void userGrabber(List<Users> output);
	
	// GRABBING SCOREBOARD
	void scoreboardGrabber(List<Users> output);
	
	// GRABBING CHALLENGES
	void challengeGrabber(List<Challenges> output);
	
	// SIGNALS LOADING IS COMPLETE (NOT FULLY IMPLEMENTED)
	void loadComplete();
}
