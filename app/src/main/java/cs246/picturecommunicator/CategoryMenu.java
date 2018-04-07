package cs246.picturecommunicator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * <h1>Category Menu</h1>
 * This is the in-between menu to select between one of 4 categories that will help simplify image searching.
 * Variables include:
 * <ul>
 *     <li>private static final String TAG: string for the tag indicating the activity name for Log</li>
 *     <li>public static final String EXTRA_CATEGORY_CHOICE: string for Extra key for intent, public so it can be accessed</li>
 *     <li>private String slotID: string to pass to next activity to identify which slot will be changed</li>
 * </ul>
 */
public class CategoryMenu extends AppCompatActivity {

    // string for the tag indicating the activity name for Log
    private static final String TAG = "CategoryMenu";

    // string for Extra key for intent, public so it can be accessed
    public static final String EXTRA_CATEGORY_CHOICE = "cs246.picturecommunicator.CATEGORY_CHOICE";

    private String slotID;

    /**
     * <h2>onCreate</h2>
     * Loads the data passed from the Main Activity and saves the slot choice to necessary variables
     * @param savedInstanceState Takes in the current state of app.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_menu);

        Log.d(TAG,"Category Menu Online");

        // Get the Intent that started this activity
        Intent intent = getIntent();

        // Extract the String
        slotID = intent.getStringExtra(MainActivity.EXTRA_SLOT_CHOICE);
        Log.d(TAG,"Selecting Category for " + slotID);

    }

    /**
     * <h2>Activities Button</h2>
     * Selects the Activities category, and passes that info and the slot choice to the next activity.
     * @param view Takes in the current viewport
     */
    public void activitiesButton(View view) {
        Intent intent = new Intent(this, ListSubMenu.class);

//        Toast t = Toast.makeText(this, "Preferences Saved Successfully", Toast.LENGTH_SHORT);
//        t.show();

        intent.putExtra(MainActivity.EXTRA_SLOT_CHOICE, slotID);
        String category = "Activities";
        intent.putExtra(EXTRA_CATEGORY_CHOICE, category);
        Log.d(TAG, category + " for " + slotID + "selected.");
        startActivity(intent);

    }

    /**
     * <h2>Food Button</h2>
     * Selects the Food category, and passes that info and the slot choice to the next activity.
     * @param view
     */
    public void foodButton(View view) {
        Intent intent = new Intent(this, ListSubMenu.class);

//        Toast t = Toast.makeText(this, "Preferences Saved Successfully", Toast.LENGTH_SHORT);
//        t.show();

        intent.putExtra(MainActivity.EXTRA_SLOT_CHOICE, slotID);
        String category = "Food";
        intent.putExtra(EXTRA_CATEGORY_CHOICE, category);
        Log.d(TAG, category + " for " + slotID + "selected.");
        startActivity(intent);

    }

    /**
     * <h2>Pain Button</h2>
     * Selects the Pain category, and passes that info and the slot choice to the next activity.
     * @param view
     */
    public void painButton(View view) {
        Intent intent = new Intent(this, ListSubMenu.class);

//        Toast t = Toast.makeText(this, "Preferences Saved Successfully", Toast.LENGTH_SHORT);
//        t.show();

        intent.putExtra(MainActivity.EXTRA_SLOT_CHOICE, slotID);
        String category = "Pain";
        intent.putExtra(EXTRA_CATEGORY_CHOICE, category);
        Log.d(TAG, category + " for " + slotID + "selected.");
        startActivity(intent);

    }

    /**
     * <h2>Family Button</h2>
     * Selects the Family category, and passes that info and the slot choice to the next activity.
     * @param view
     */
    public void familyButton(View view) {
        Intent intent = new Intent(this, ListSubMenu.class);

//        Toast t = Toast.makeText(this, "Preferences Saved Successfully", Toast.LENGTH_SHORT);
//        t.show();

        intent.putExtra(MainActivity.EXTRA_SLOT_CHOICE, slotID);
        String category = "Family";
        intent.putExtra(EXTRA_CATEGORY_CHOICE, category);
        Log.d(TAG, category + " for " + slotID + "selected.");
        startActivity(intent);

    }

}
