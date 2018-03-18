package cs246.picturecommunicator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CategoryMenu extends AppCompatActivity {

    // string for the tag indicating the activity name for Log
    private static final String TAG = "CategoryMenu";

    // string for Extra key for intent, public so it can be accessed
    public static final String EXTRA_CATEGORY_CHOICE = "cs246.picturecommunicator.CATEGORY_CHOICE";

    private String slotID;

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

    public void activitiesButton(View view) {
        Intent intent = new Intent(this, PictureSelector.class);

//        Toast t = Toast.makeText(this, "Preferences Saved Successfully", Toast.LENGTH_SHORT);
//        t.show();

        intent.putExtra(MainActivity.EXTRA_SLOT_CHOICE, slotID);
        String category = "Activities";
        intent.putExtra(EXTRA_CATEGORY_CHOICE, slotID);
        startActivity(intent);

    }

}
