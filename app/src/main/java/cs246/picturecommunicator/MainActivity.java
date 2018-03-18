package cs246.picturecommunicator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // string for the tag indicating the activity name for Log
    private static final String TAG = "MainActivity";

    // string for Extra key for intent, public so it can be accessed
    public static final String EXTRA_SLOT_CHOICE = "cs246.picturecommunicator.SLOT_CHOICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Template Button Function
    public void sampleButton(View view) {

        /*
            Link to next activity via Intent
            Context as first parameter (this) (is an Activity, subclass of Context)
            Second Parameter: Class of the component to which the Intent should be delivered
         */
        Intent intent = new Intent(this, CategoryMenu.class);

        String slotID = "Slot_0";

//        Toast t = Toast.makeText(this, "Preferences Saved Successfully", Toast.LENGTH_SHORT);
//        t.show();

        intent.putExtra(EXTRA_SLOT_CHOICE, slotID);
        startActivity(intent);
    }

}

