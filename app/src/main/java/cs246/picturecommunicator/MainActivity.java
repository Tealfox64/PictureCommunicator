package cs246.picturecommunicator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Main activity class to generate buttons to
 * go from request activity to Picture Selector Activity.
 * Each layout will hold all requests until further deletion.
 */
public class MainActivity extends AppCompatActivity {

    // string for Extra key for intent, public so it can be accessed
    public static final String EXTRA_SLOT_CHOICE = "cs246.picturecommunicator.SLOT_CHOICE";

    // 4 buttons represent four activities


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Anthony - We need to run the three tests to check whether or not a previous slot has been chosen
        if (isSlot1Empty()) {

        }

        if (isSlot2Empty()) {

        }

        if (isSlot3Empty()) {

        }
    }

    /**
     *
     */
    public void openPictureMenu(View view) {
        Intent intent = new Intent (this, CategoryMenu.class);
        startActivity(intent);
    }
//  TODO: Anthony - We need to create three functions: One for each slot



    public boolean isSlot1Empty() {
        return true;
    }

    public boolean isSlot2Empty() {
        return true;
    }

    public boolean isSlot3Empty() {
        return true;
    }
}

