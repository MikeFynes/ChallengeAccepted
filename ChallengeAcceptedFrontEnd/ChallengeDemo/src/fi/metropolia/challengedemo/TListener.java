/*
 * THIS IS A REQUIRED FEATURE OF THE ACTIONBAR TABS THAT ALLOWS IT TO KNOW WHICH TAB IS IN USE
 * 
 * THE FRAGMENT IS ASSIGNED A LISTENER INSTANCE (ONE FOR EACH TAB) WHICH THEN ACTS AS AN EVENT
 * LISTENER FOR onTabSelected EVENTS
 * 
 * 
*/

package fi.metropolia.challengedemo;

/**
 * Created by mike on 18/11/2014.
 */
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class TListener implements ActionBar.TabListener {

    private Fragment mFragment;


    public TListener(Fragment frag) {
        mFragment = frag;
    }


    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        ft.replace(R.id.frag_container, mFragment);
    }

    public void onTabUnselected(Tab tab, FragmentTransaction ft) {

    }

    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }

}
