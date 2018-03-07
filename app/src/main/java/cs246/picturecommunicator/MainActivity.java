package cs246.picturecommunicator;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_SLOT1 = "cs246.picturecommunicator.SLOT_1";
    public static final String PREF_SLOT2 = "cs246.picturecommunicator.SLOT_2";
    public static final String PREF_SLOT3 = "cs246.picturecommunicator.SLOT_3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void savePrefs(View view) {

        Context context = getBaseContext();
        SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        // Fill editText with address to the EditText
        EditText testText1 = (EditText) findViewById(R.id.testText1);
        EditText testText2 = (EditText) findViewById(R.id.testText2);
        EditText testText3 = (EditText) findViewById(R.id.testText3);

        // Retrieve the message from the EditText
        String text1 = testText1.getText().toString();
        String text2 = testText2.getText().toString();
        String text3 = testText3.getText().toString();

        prefEditor.putString(PREF_SLOT1, text1);
        prefEditor.putString(PREF_SLOT2, text2);
        prefEditor.putString(PREF_SLOT3, text3);

        prefEditor.apply();

        Toast t = Toast.makeText(this, "Scripture Saved Successfully", Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    protected void onPause() {
        super.onPause();


    }
}

