/*
 * THIS IS THE FIRST ACTIVITY THAT RUNS ON STARTUP
 * 
 * THE USER LIST IS LOADED HERE TO CREATE THE USER SELECTION OPTIONS
 * 
 * THIS CAN IN THE FUTURE BE REPLACED BY A LOGIN SYSTEM THAT WOULD REMOVE THE NEED 
 * FOR AN ASYNC TASK TO RUN HERE ON CREATE
 * 
 * 
 */

package fi.metropolia.challengedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Start extends Activity implements View.OnClickListener,
		AsyncResponse {

	/*
	 * HERE THE VARIOUS ASYNC TASKS THAT GET DATA FROM THE BACKEND ARE DECLARED
	 * AND INITIALISED
	 */

	AsyncGetUsers asyncUserGrab = new AsyncGetUsers();

	List<Users> usersList;

	View myView;
	ImageButton btn_One, btn_Two;
	private int user;
	TableLayout tableLayout;
	Button btnAddUser;
	String userName;

	public static boolean userLoadFinished = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		/*
		 * HERE THE VARIOUS ASYNC TASKS THAT GET DATA FROM THE BACKEND ARE
		 * ASSIGNED LISTENERS AND EXECUTED
		 */

		asyncUserGrab.listener = this;

		asyncUserGrab.execute("allUsers");
		usersList = new ArrayList<Users>();

		try {
			usersList = asyncUserGrab.get();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnAddUser = (Button) findViewById(R.id.btn_add_user);
		btnAddUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				goToAddUser();
				
				finish();
				;
			}
		});

		readUsersFromDataBase();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void goToAddUser() {
		Intent intent = new Intent(this, AddUserActivity.class);
		startActivity(intent);
	}

	public void startSecondAct() {

		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("userID", getUser());
// THIS ALLOWS THE WELCOME PAGE TO DIFFER DEPENDING ON ACTIVITY THE USER IS COMING FROM
		intent.putExtra("nav", "start");
		startActivity(intent);

	}

	public int getUser() {

		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	@Override
	public void userGrabber(List<Users> output) {
		// TODO Auto-generated method stub
		usersList = output;
	}

	public List<Users> getUsersList() {

		return usersList;
	}

	public void readUsersFromDataBase() {

		// FINDS TABLE LAYOUT
		tableLayout = (TableLayout) findViewById(R.id.user_list);

		
		int numRows = usersList.size();

		// For loop creates each table item
		for (int i = 0; i < numRows; i++) {

			TableRow row = new TableRow(this);
			TableRow.LayoutParams params = new TableRow.LayoutParams(
					TableRow.LayoutParams.FILL_PARENT,
					TableRow.LayoutParams.WRAP_CONTENT);

			row.setLayoutParams(params);
			row.setGravity(Gravity.CENTER | Gravity.BOTTOM);
			row.setBackgroundColor(getResources().getColor(R.color.actionbar));
			row.setPadding(1, 1, 1, 1);

			// USERNAME COLUMN
			TextView tv1 = new TextView(this);
			tv1.setTextSize(40);
			tv1.setPadding(25, 0, 25, 0);

			final String dbUser = usersList.get(i).getName();
			tv1.setText(dbUser);

			final int userPosition = i;
			row.addView(tv1);

			row.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					setUser(userPosition);
					setUserName(dbUser);

					startSecondAct();
				}
			});

			TableRow rowGap = new TableRow(this);

			rowGap.setLayoutParams(params);
			rowGap.setGravity(Gravity.CENTER | Gravity.BOTTOM);
			rowGap.setBackgroundColor(getResources().getColor(R.color.white));
			rowGap.setPadding(1, 1, 1, 1);

			tableLayout.addView(row);
			tableLayout.addView(rowGap);

		}

	}

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void challengeGrabber(List<Challenges> output) {
		// TODO Auto-generated method stub

		// NOT USED BY THIS ACTIVITY

	}

	@Override
	public void loadComplete() {
		// TODO Auto-generated method stub
		userLoadFinished = true;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void scoreboardGrabber(List<Users> output) {
		// TODO Auto-generated method stub

	}

}
