package fi.metropolia.challengedemo;

import java.util.List;

/**
 * Created by mike on 18/11/2014.
 */
public interface AsyncResponse {
    // GRABBING USERS
	void userGrabber(List<Users> output);
	void challengeGrabber(List<Challenges> output);

}
