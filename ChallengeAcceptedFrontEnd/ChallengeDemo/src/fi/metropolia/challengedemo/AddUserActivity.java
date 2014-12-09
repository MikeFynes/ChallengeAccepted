package fi.metropolia.challengedemo;

//THIS CLASS CREATES A SIMPLE FORM ACTIVITY, THE FORM THEN EXITS THIS ACTIVITY ON COMPLETION AND TRIGGERS THE USER SELECTION MENU


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class AddUserActivity extends Activity {
	Button btnComplete;
	EditText etUserName;
	String uName;
	View myView;
	
	AsyncAddUser asyncAddUser;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_add_user);
	        
	        
	        // THE ASYNC TASK CLASS ALLOWS A NEW USER TO BE ADDED TO THE BACKEND DATABASE
	        asyncAddUser = new AsyncAddUser();
	        
	        etUserName = (EditText)findViewById(R.id.et_username);
	        
	        btnComplete = (Button) findViewById(R.id.btn_complete_add);
	        btnComplete.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	uName = etUserName.getText().toString();
	            	if(uName !=null){
	            		asyncAddUser.execute(uName);
	            	}
	            	else{
	            		 Toast.makeText(myView.getContext(), "No User Name!", Toast.LENGTH_LONG).show();
	            	}
	            	
	            	start();
	            	finish();
	           	   
	                
	                ;
	            } }
	        )
	        ;
	        
}
	 
	 public void start(){
	    	Intent intent = new Intent(this, Start.class);
	    	startActivity(intent);
	 }
	 
}
