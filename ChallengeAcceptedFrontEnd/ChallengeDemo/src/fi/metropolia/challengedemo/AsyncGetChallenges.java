/*THIS TASK ALLOWS ALL CHALLENGES STORED IN THE DATABASE TO BE RETRIEVED FROM THE BACKEND
 * 
 * THE METHOD IS SENT TO THE BACKEND AND INFORMS THE BACKEND WHAT DATA TO EXPECT AND WHAT TO DO WITH IT
 * 
 * IN THIS CASE THE BACKEND EXPECTS ONLY A METHOD WHICH IS PROVIDEd THROUGH THE EXECUTE STATEMENT
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

class AsyncGetChallenges extends AsyncTask<String, Integer, List<Challenges>> {
    public AsyncResponse delegate=null;

    
   
    List<Challenges> challengesList;

    AsyncResponse listener;
    
    @Override
    protected List<Challenges> doInBackground(String... params) {
        // TODO Auto-generated method stub

        postData(params[0]);
  
        return challengesList;
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
            
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            Log.d("HTTP POST", "SENT THE POST");

            Header reply = response.getFirstHeader("backEnd") ;
            if(reply !=null){
                String postReply = reply.getValue();
                Log.d("HTTP RESPONSE",  postReply);
/*
 * HERE THE RESPONSE IS VERY IMPORTANT
 * 
 * THIS FOR LOOP IS EFFECTIVELY A REVERSE OF THE FOR LOOP IN THE BACK END AS IT TAKES THE
 * 			STRINGS -> OBJECT -> LIST
 * 
 * 
*/
                if(response.containsHeader("challenges")){
                    challengesList = new ArrayList<Challenges>();
                    for(int i=0; i < Integer.parseInt(response.getFirstHeader("itemCount").getValue()); i++){
                       int challId = Integer.parseInt(response.getFirstHeader("cId"+Integer.toString(i)).getValue());
                       String challName = response.getFirstHeader("cName"+Integer.toString(i)).getValue();
                       String challDesc = response.getFirstHeader("cDesc"+Integer.toString(i)).getValue();
                       int challPoints =Integer.parseInt(response.getFirstHeader("cPoints"+Integer.toString(i)).getValue());
                       String challCategory = response.getFirstHeader("cCategory"+Integer.toString(i)).getValue();


                        Challenges nChallenges = new Challenges(challId, challPoints , challName , challDesc, challCategory );
                        challengesList.add(nChallenges);
                        
                        Log.d("CHALLENGES",   nChallenges.getName() +""
                        		+ " ID "+ Integer.toString(nChallenges.getId()) 
                        		+" DESCRIPTION "+ nChallenges.getDescription() 
                        		+ " POINTS " + nChallenges.getPoints() + " CATEGORY " + nChallenges.getCategory() );

                    }

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
        	Log.d("POST FAIL", "NO POST SENT CLIENTPROTOCOL");
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	Log.d("POST FAIL", "NO POST SENT IOEXCEPTION");
        }






    }


	/*
	 * THE LIST THAT IS COMPLETED AS PART OF THE POST RESPONSE ENDS UP HERE
	 * 
	 * THIS SENDS THE COMPLETED LIST THROUGH THE INTERFACE WHERE IT CAN THEN BE 
	 * USED BY THE OTHER ACTIVITIES
	 * 
	 * 
	*/

    protected void onPostExecute(List<Challenges> result) {
        listener.challengeGrabber(result);
    }






	public List<Challenges> getChallengesList() {
		return challengesList;
	}






	public void setChallengesList(List<Challenges> challengesList) {
		this.challengesList = challengesList;
	}






	
}
