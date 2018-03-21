package cs246.picturecommunicator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Main activity class to generate buttons to
 * go from request activity to Picture Selector Activity.
 * Each layout will hold all requests until further deletion.
 */
public class MainActivity extends AppCompatActivity {

    // 4 buttons represent four activities
    private Button famButton;           // family
    private Button painButton;          // pain
    private Button foodButton;          // food
    private Button activitiesButton;    // activities

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selector);

        famButton = (Button) findViewById(R.id.familyButton);
        famButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPictureMenu();
            }
        });
    }

    /**
     *
     */
    public void openPictureMenu() {
        Intent intent = new Intent (this, PictureSelector.class);
        startActivity(intent);
    }

}

