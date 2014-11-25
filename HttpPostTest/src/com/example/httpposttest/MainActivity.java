package com.example.httpposttest;

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
import org.apache.http.util.EntityUtils;

import com.example.httpposttest.R.id;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
private Button buttonPost, buttonCheck, buttonChallenge, buttonReceived, buttonComplete;
private TextView textView;
MyAsyncTask asyncTask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		buttonPost = (Button) findViewById(R.id.button);
		buttonPost.setOnClickListener(this);
		textView = (TextView) findViewById(R.id.text);
		
		buttonCheck = (Button) findViewById(R.id.buttonCheck);
		buttonCheck.setOnClickListener(this);
		
		buttonChallenge = (Button) findViewById(R.id.buttonChallenge);
		buttonChallenge.setOnClickListener(this);
		
		buttonReceived = (Button) findViewById(R.id.buttonReceived);
		buttonReceived.setOnClickListener(this);
		
		buttonComplete = (Button) findViewById(R.id.buttonComplete);
		buttonComplete.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		
		return true;
	}
	
	
	/*private void makePostRequest() {
		 
        
        HttpClient httpClient = new DefaultHttpClient();
                                // replace with your url
        HttpPost httpPost = new HttpPost("http://10.0.2.2/HandlerServlet"); 
 
 
        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("test", "THIS IS THE TEST MESSAGE"));
        Log.d("HTTP FAIL", "TEST MESSAGE CREATED");
 
         
        //Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
            Log.d("HTTP FAIL", e.toString());
        }
 
        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            // write response to log
            Log.d("Http Post Response:", response.toString());
            textView.setText(response.toString());
        } catch (ClientProtocolException e) {
            // Log exception
        	 e.printStackTrace();
           Log.d("HTTP FAIL", e.toString());
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
            Log.d("HTTP FAIL", e.toString());
            
        }
        
     
 
    }*/
	
	
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		
		case R.id.button:
			new MyAsyncTask().execute("allChallenges");
			break;
		case R.id.buttonCheck:
			new MyAsyncTask().execute("allUsers");
			break;
		case R.id.buttonChallenge:
			new MyAsyncTask().execute("challengeUser");
			break;
		case R.id.buttonReceived:
			new MyAsyncTask().execute("checkIfChallenged");
		break;
		case R.id.buttonComplete:
			new MyAsyncTask().execute("completeChallenge");
		break;
		
		}
	}

	



	

private class MyAsyncTask extends AsyncTask<String, Integer, Double> {
	
	
    @Override
    protected Double doInBackground(String... params) {
        // TODO Auto-generated method stub
       postData(params[0]);
       
       
        return null;
    }

    
    public void postData(String valueIWantToSend) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(
                "http://10.0.2.2:8080/ChallengeAcceptedBackEnd/HandlerServlet");
        Log.d("HTTP POST", "I CREATED A POST");
        Log.d("HTTP POST", valueIWantToSend);
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("method",
            		valueIWantToSend));
            if(valueIWantToSend.contentEquals("challengeUser")){
            	nameValuePairs.add(new BasicNameValuePair("User", "2"));
            	nameValuePairs.add(new BasicNameValuePair("Challenge", "2" ));
            }
            else if (valueIWantToSend.contentEquals("checkIfChallenged")){
            	nameValuePairs.add(new BasicNameValuePair("User", "2"));
            }
            else if(valueIWantToSend.contentEquals("completeChallenge")){
            	nameValuePairs.add(new BasicNameValuePair("User", "2"));
            }
    
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            Log.d("HTTP POST", "SENT THE POST");
                            
           Header reply = response.getFirstHeader("backEnd") ;
            if(reply !=null){
            	String postReply = reply.getValue();
            	Log.d("HTTP RESPONSE",  postReply);
            	

            	if(response.containsHeader("users")){
            		for(int i=0; i < Integer.parseInt(response.getFirstHeader("itemCount").getValue()); i++){
            		int userId = Integer.parseInt(response.getFirstHeader("uId"+Integer.toString(i)).getValue());
            		int totalPoints = Integer.parseInt(response.getFirstHeader("uTotPoints"+Integer.toString(i)).getValue());
            		int currentChallenge = Integer.parseInt(response.getFirstHeader("uCurrentChallenge"+Integer.toString(i)).getValue());
            		String userName = response.getFirstHeader("uName"+Integer.toString(i)).getValue();
            		Boolean active = Boolean.valueOf(response.getFirstHeader("uActive"+Integer.toString(i)).getValue());
            		Boolean notified = Boolean.valueOf(response.getFirstHeader("uNotif"+Integer.toString(i)).getValue());
            		
           		Log.d("USERS",   userName +"ID: "
           		+Integer.toString(userId) +", Total Points: "
           		+ Integer.toString(totalPoints) +", CurrentChallengeID: "
           		+ Integer.toString(currentChallenge) +", Active? "
           		+ Boolean.toString(active) +", Notified? "
           		+ Boolean.toString(notified));
           		}
           		
            		
            		Log.d("USERS RECEIVED", response.getFirstHeader("users").getValue());
                		
            	}
            	else if(response.containsHeader("challenges")){
            		for(int i=0; i < Integer.parseInt(response.getFirstHeader("itemCount").getValue()); i++){
                		int challId = Integer.parseInt(response.getFirstHeader("cId"+Integer.toString(i)).getValue());
                		String challName = response.getFirstHeader("cName"+Integer.toString(i)).getValue();
                		String challDesc = response.getFirstHeader("cDesc"+Integer.toString(i)).getValue();
                		int challPoints =Integer.parseInt(response.getFirstHeader("cPoints"+Integer.toString(i)).getValue());
                		Log.d("CHALLENGES",   challName +" ID "+ Integer.toString(challId) +" DESCRIPTION "+ challDesc + " POINTS " + challPoints );  		
                			

         	}
            		
            	}
            	else if(response.containsHeader("challChecker")){
            		String userId = response.getFirstHeader("checkUser").getValue();
            		Boolean challenged = Boolean.valueOf(response.getFirstHeader("checker").getValue());
            		Log.d("CHECKER",   userId +" challenge status: "+ Boolean.toString(challenged));  
            	}
            	
            	else if(response.containsHeader("trueStory")){
            		String accepted = response.getFirstHeader("trueStory").getValue();
            		Log.d("CAN YOU DO IT?", accepted);
            	}
            	else if(response.containsHeader("completed")){
            		String accepted = response.getFirstHeader("completed").getValue();
            		Log.d("CAN YOU DO IT?", accepted);
            	}
            	
            	
            	
            	else{
            		Log.d("HTTP RESPONSE", "NO VALID RESPONSE METHOD");
            	}
            	
            	
            
        
            }
            
            
            else{
            	Log.d("HTtP RESPONSE", "NO RESPONSE HEADERS");
            	
            }
            
            	
            
            	
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        

        
        
    }

    

}
	
	
	
	
	

}
