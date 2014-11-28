package fi.metropolia.challengedemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncChallengeResponse extends AsyncTask<Integer, Boolean, String> {
    public AsyncResponse delegate=null;
    AsyncResponse listener;
    private int userId, challResponse;
    
    
 

   





	@Override
    protected String doInBackground(Integer... params) {
        // TODO Auto-generated method stub
    	setUserId(params[0]);
    	setChallResponse(params[1]);
    	String method = "challengeResponse";
        postData(method);
        
        
        return null;
    }





    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}



	public int getChallResponse() {
		return challResponse;
	}





	public void setChallResponse(int params) {
		this.challResponse = params;
	}





	public void postData(String valueIWantToSend) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(
                "http://10.0.2.2:8080/ChallengeAcceptedBackEnd/HandlerServlet");
        Log.d("HTTP POST", "I CREATED A POST");
        String postUser = Integer.toString(getUserId());
        String postResponse = Integer.toString(getChallResponse());
        Log.d("HTTP POST", postUser);
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("method",
            		valueIWantToSend));
            
                nameValuePairs.add(new BasicNameValuePair("User", postUser));
                nameValuePairs.add(new BasicNameValuePair("status", postResponse));
               
            
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            Log.d("HTTP POST", "SENT THE POST");

            
            



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        	Log.d("POST FAIL", "NO POST SENT CLIENTPROTOCOL");
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	Log.d("POST FAIL", "NO POST SENT IOEXCEPTION");
        }






    } 
	
}
