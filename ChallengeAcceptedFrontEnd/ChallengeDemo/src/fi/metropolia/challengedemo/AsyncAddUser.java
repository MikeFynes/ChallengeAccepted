/*THIS CLASS ALLOWS A USER TO BE ADDED AND IS A SIMPLE ASYNCRONOUS HTTP POST TASK
 * 
 * THE METHOD IS SENT TO THE BACKEND AND INFORMS THE BACKEND WHAT DATA TO EXPECT AND WHAT TO DO WITH IT
 * 
 * */




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





class AsyncAddUser extends AsyncTask<String, Integer, String> {
    public AsyncResponse delegate=null;
    private String uName;
    
   
    

    AsyncResponse listener;
    
    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
    	
    	setuName(params[0]);
    	String method = "addUser";
        postData(method);
        
        return null;
    }






    public String getuName() {
		return uName;
	}






	public void setuName(String uName) {
		this.uName = uName;
	}






	public void postData(String valueIWantToSend) {
		
		// POST IS INITIALISED
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(
        		 "http://10.0.2.2:8080/ChallengeAcceptedBackEnd/HandlerServlet");
        Log.d("HTTP POST", "I CREATED A POST");
        Log.d("HTTP POST", valueIWantToSend);
        try {
            /*HERE THE DATA WE WANT TO SEND IS ADD TO AN ARRAYLIST OF NAMED VALUE PAIRS
             * THIS ALLOWS THE DATA TO BE TRANSMITTED AS A POST REQUEST IN THE FORMAT
             * HEADER / VALUE
             * 
             * 
             * */
        	
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("method",
                    valueIWantToSend));
            nameValuePairs.add(new BasicNameValuePair("uName",
                    uName));
            
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Executes the HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            Log.d("HTTP POST", "SENT THE POST");

            
            /*READING RESPONSE
             * 
             * RESPONSE IS READ AS STRINGS AS ALL POST DATA IS IN STRING FORMAT
             * 
             * 
             * */
            Header reply = response.getFirstHeader("backEnd") ;
            if(reply !=null){
                String postReply = reply.getValue();
                Log.d("HTTP RESPONSE",  postReply);

            }


            else{
                Log.d("HTtP RESPONSE", "NO RESPONSE HEADERS");

            }




        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        	Log.d("POST FAIL", "NO POST SENT CLIENTPROTOCOL");
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	Log.d("POST FAIL", "NO POST SENT IOEXCEPTION");
        }






    }


	
	





	
}
