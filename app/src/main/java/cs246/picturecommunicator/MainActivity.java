package cs246.picturecommunicator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * <h1>Main Activity</h1>
 * Main Activity is what the user sees as they start the app.
 * <p>
 * By default, there are 6 buttons, 3 buttons that will contain the images
 * (they begin with plus signs) and 3 buttons beside each of them that
 * reset the button to the plus sign image.
 * <p>
 * Variables include:
 * <ul>
 *     <li>public static String slot#Category: string variables used in ListSubMenu to define each slot's category</li>
 *     <li>public static final String SLOT#_RESID: Slot ImageRESID Tags for Shared Preferences</li>
 *     <li>ImageButton variables: interact with the image and reset buttons</li>
 *     <li>private static final String TAG: holds the tag name for debugging</li>
 *     <li>public static final String EXTRA_SLOT_CHOICE: string for Extra key for intent, public so it can be accessed</li>
 *     <li>public static String FILENAME: location of the picture data list</li>
 *     <li>public static List #####List: 4 array lists to store picture data</li>
 * </ul>
 *
 */
public class MainActivity extends AppCompatActivity {


    // Slot Categories - TODO: LOOK AT THIS WE MAY NOT NEED THIS, but it is used in ListSubMenu
    public static String slot1Category;
    public static String slot2Category;
    public static String slot3Category;


    // Slot ImageRESID Tags for Shared Preferences
    public static final String SLOT1_RESID = "slot1RES";
    public static final String SLOT2_RESID = "slot2RES";
    public static final String SLOT3_RESID = "slot3RES";

    // Image Button Variables
    ImageButton imgButton1;
    ImageButton imgButton2;
    ImageButton imgButton3;
    ImageButton resetButton1;
    ImageButton resetButton2;
    ImageButton resetButton3;

    // string for the tag indicating the activity name for Log
    private static final String TAG = "MainActivity";

    // string for Extra key for intent, public so it can be accessed
    public static final String EXTRA_SLOT_CHOICE = "cs246.picturecommunicator.SLOT_CHOICE";

    // location of the picture data file
    public static String FILENAME = "listimages.txt";

    // 4 lists to store picture data
    public static List activitiesList = new ArrayList<PictureHolder>();
    public static List foodList = new ArrayList<PictureHolder>();
    public static List painList = new ArrayList<PictureHolder>();
    public static List familyList = new ArrayList<PictureHolder>();

    /**
     * <h2>OnCreate</h2>
     * This first function will create the lists of images, and set up the buttons based on the images
     * stored in SharedPreferences. It will also start the OnClickListeners for each of the reset buttons
     * @param savedInstanceState Takes in the current state of app.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // parse the data file into the lists
        loadImageLists();

        // activate shared preferences retrieval
        final SharedPreferences sharedpreferences = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);

        // change the background of the buttons to the images stored in shared preferences, if they are there.
        // if there are no shared preferences, set the background to a plus sign.

        // slot 1 check
        if (!sharedpreferences.contains(SLOT1_RESID)) {
            imgButton1 = findViewById(R.id.imageButton1);
            imgButton1.setBackgroundResource(android.R.drawable.ic_input_add);
        } else {
            imgButton1 = findViewById(R.id.imageButton1);
            int temp = sharedpreferences.getInt(SLOT1_RESID,-1);
            if (temp != -1)
                imgButton1.setBackgroundResource(getResources().getIdentifier(Integer.toString(temp), "drawable", getPackageName()));
        }

        // slot 2 check
        if (!sharedpreferences.contains(SLOT2_RESID)) {
            imgButton2 = findViewById(R.id.imageButton2);
            imgButton2.setBackgroundResource(android.R.drawable.ic_input_add);
        } else {
            imgButton2 = findViewById(R.id.imageButton2);
            int temp = sharedpreferences.getInt(SLOT2_RESID,-1);
            if (temp!= -1)
                imgButton2.setBackgroundResource(getResources().getIdentifier(Integer.toString(temp), "drawable", getPackageName()));
        }

        // slot 3 check
        if (!sharedpreferences.contains(SLOT3_RESID)) {
            imgButton3 = findViewById(R.id.imageButton3);
            imgButton3.setBackgroundResource(android.R.drawable.ic_input_add);
        } else {
            imgButton3 = findViewById(R.id.imageButton3);
            int temp = sharedpreferences.getInt(SLOT3_RESID,-1);
            if (temp != -1)
                imgButton3.setBackgroundResource(getResources().getIdentifier(Integer.toString(temp), "drawable", getPackageName()));
        }

        // Clear buttons to clear the image and shared preferences
        resetButton1 = findViewById(R.id.removeButton1);
        resetButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgButton1.setBackgroundResource(android.R.drawable.ic_input_add);
//                sharedpreferences.edit().clear().apply();
            }
        });
        resetButton2 = findViewById(R.id.removeButton2);
        resetButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgButton2.setBackgroundResource(android.R.drawable.ic_input_add);
//                sharedpreferences.edit().clear().apply();
            }
        });
        resetButton3 = findViewById(R.id.removeButton3);
        resetButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgButton3.setBackgroundResource(android.R.drawable.ic_input_add);
//                sharedpreferences.edit().clear().apply();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        final SharedPreferences sharedpreferences = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        sharedpreferences.edit().clear().apply();
    }

    /**
     * <h2>loadImageLists</h2>
     * Performs the arduous task of accessing the list text file and parsing the data for each image into
     * one of 4 lists that represent each image category, with their names and filepaths ready.
     */
    protected void loadImageLists(){
        // read an entire line from a file, expects some kind of input stream
        Log.d(TAG,"Creating BufferedReader");
        // specific manager for assets
        AssetManager am = getAssets();
        try(BufferedReader finReader = new BufferedReader(new InputStreamReader(am.open(FILENAME)))) {
            Log.d(TAG,"Entered BufferedReader");

            String line;
            PictureHolder picture;
            String filepath;
            String label;
            String category;

            Log.d(TAG,"Looping through " + FILENAME);
            // create String outside of scope, and use a boolean expression
            while ( (line = finReader.readLine()) != null ) { // readline() strips the \n from the data

                // parse filepath, label, and category at each comma
                filepath = "";
                int i;
                for (i = 0; line.charAt(i) != ',' && i < line.length(); i++)
                {
                    filepath += line.charAt(i);
                }
                i++;

                label = "";
                for (; line.charAt(i) != ',' && i < line.length(); i++)
                {
                    label += line.charAt(i);
                }
                i++;

                category = "";
                for (; i < line.length(); i++)
                {
                    category += line.charAt(i);
                }

                // put the parsed variables into a pictureHolder object
                picture = new PictureHolder();
                picture.filename = getResources().getIdentifier(filepath,"drawable", getPackageName());
                picture.label = label;
                picture.category = category;

                Log.d(TAG,"Object created: \n  " + picture.category + " --- " + picture.label + " --- " +  picture.filename);

                // determine which list to add the pictureHolder object to by category
                switch (picture.category)
                {
                    case "activities":
                        activitiesList.add(picture);
                        break;
                    case "food":
                        foodList.add(picture);
                        break;
                    case "pain":
                        painList.add(picture);
                        break;
                    case "family":
                        familyList.add(picture);
                        break;
                    default:
                        break;
                }

            }

            // THE REST IS FOR DEBUGGING PURPOSES
            Log.d(TAG,"*****Verifying contents of each list*****");
            // ASSUMING LISTS ARE ARRAY LISTS!!!
            for (int i = 0; i < activitiesList.size(); i++) {
                Log.d(TAG,"activitiesList[" + i + "] --- " + ((PictureHolder)activitiesList.get(i)).getLabel() + ": " +  ((PictureHolder)activitiesList.get(i)).getFilename());
            }

            for (int i = 0; i < foodList.size(); i++) {
                Log.d(TAG,"foodList[" + i + "] --- " + ((PictureHolder)foodList.get(i)).getLabel() + ": " +  ((PictureHolder)foodList.get(i)).getFilename());
            }

            for (int i = 0; i < painList.size(); i++) {
                Log.d(TAG,"painList[" + i + "] --- " + ((PictureHolder)painList.get(i)).getLabel() + ": " +  ((PictureHolder)painList.get(i)).getFilename());
            }

            for (int i = 0; i < familyList.size(); i++) {
                Log.d(TAG,"familyList[" + i + "] --- " + ((PictureHolder)familyList.get(i)).getLabel() + ": " +  ((PictureHolder)familyList.get(i)).getFilename());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }

    /**
     * <h2>slotOneButton</h2>
     * Moves to the next activity, CategoryMenu, and passes along Slot 1 ID info
     * @param view Takes in the current viewport
     */
    public void slotOneButton(View view) {
         /*
            Link to next activity via Intent
            Context as first parameter (this) (is an Activity, subclass of Context)
            Second Parameter: Class of the component to which the Intent should be delivered
         */
        Intent intent = new Intent(this, CategoryMenu.class);
        String slotID = "Slot_1";
        intent.putExtra(EXTRA_SLOT_CHOICE, slotID);
        startActivity(intent);
    }

    /**
     * <h2>slotTwoButton</h2>
     * Moves to the next activity, CategoryMenu, and passes along Slot 2 ID info
     * @param view Takes in the current viewport
     */
    public void slotTwoButton(View view) {
         /*
            Link to next activity via Intent
            Context as first parameter (this) (is an Activity, subclass of Context)
            Second Parameter: Class of the component to which the Intent should be delivered
         */
        Intent intent = new Intent(this, CategoryMenu.class);
        String slotID = "Slot_2";
        intent.putExtra(EXTRA_SLOT_CHOICE, slotID);
        startActivity(intent);
    }

    /**
     * <h2>slotThreeButton</h2>
     * Moves to the next activity, CategoryMenu, and passes along Slot 3 ID info
     * @param view Takes in the current viewport
     */
    public void slotThreeButton(View view) {
         /*
            Link to next activity via Intent
            Context as first parameter (this) (is an Activity, subclass of Context)
            Second Parameter: Class of the component to which the Intent should be delivered
         */
        Intent intent = new Intent(this, CategoryMenu.class);
        String slotID = "Slot_3";
        intent.putExtra(EXTRA_SLOT_CHOICE, slotID);
        startActivity(intent);
    }

}

