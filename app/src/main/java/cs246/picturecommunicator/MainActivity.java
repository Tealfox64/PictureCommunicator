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
    private ImageButton request1;
    private ImageButton request2;
    private ImageButton request3;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //request1 = (ImageButton) findViewById(R.id.imageButton1);
        //request1.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        openPictureMenu();
        //    }
        //});
    }

    /**
     *
     */
    public void openPictureMenu(View view) {
        Intent intent = new Intent (this, CategoryMenu.class);
        startActivity(intent);
    }

}

