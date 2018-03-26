package cs246.picturecommunicator;

import android.content.Intent;
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
 * Main activity class to generate buttons to
 * go from request activity to Picture Selector Activity.
 * Each layout will hold all requests until further deletion.
 */
public class MainActivity extends AppCompatActivity {
    // variables to choose which image to use when request has been made
    public static boolean isSlot1Empty = true;
    public static boolean isSlot2Empty = true;
    public static boolean isSlot3Empty = true;
    public static String imagePath;

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
     *  Displays the three request categories. When
     *  request has been made, category request image will change to chosen
     *  request
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // parse the data file into the lists
        loadImageLists();

        // three tests to check whether or not a previous slot has been chosen
        if (Slot1Empty()) {
            ImageButton imgButton = (ImageButton) findViewById(R.id.imageButton1);
            imgButton.setImageResource(android.R.drawable.ic_input_add);
        } else {
            ImageButton imgButton = (ImageButton) findViewById(R.id.imageButton1);
            imgButton.setImageResource(getResources().getIdentifier(imagePath, "drawable", getPackageName()));
        }

        if (Slot2Empty()) {
            ImageButton imgButton = (ImageButton) findViewById(R.id.imageButton2);
            imgButton.setImageResource(android.R.drawable.ic_input_add);
        } else {
            ImageButton imgButton = (ImageButton) findViewById(R.id.imageButton2);
            imgButton.setImageResource(getResources().getIdentifier(imagePath, "drawable", getPackageName()));
        }

        if (Slot3Empty()) {
            ImageButton imgButton = (ImageButton) findViewById(R.id.imageButton3);
            imgButton.setImageResource(android.R.drawable.ic_input_add);
        } else {
            ImageButton imgButton = (ImageButton) findViewById(R.id.imageButton3);
            imgButton.setImageResource(getResources().getIdentifier(imagePath, "drawable", getPackageName()));
        }
    }

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

                picture = new PictureHolder();
                picture.filename = getResources().getIdentifier(filepath,"drawable", getPackageName());
                picture.label = label;
                picture.category = category;

                Log.d(TAG,"Object created: \n  " + picture.category + " --- " + picture.label + " --- " +  picture.filename);

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

            // Verify by debugging
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
     * Opening the category menu when one of the request button images has been pressed
     */

    public void sampleButton(View view) {
         /*
            Link to next activity via Intent
            Context as first parameter (this) (is an Activity, subclass of Context)
            Second Parameter: Class of the component to which the Intent should be delivered
         */
        Intent intent = new Intent(this, CategoryMenu.class);
        String slotID = "Slot_0";
        intent.putExtra(EXTRA_SLOT_CHOICE, slotID);
        startActivity(intent);
    }

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

//  TODO: Anthony - We need to create three functions: One for each slot
    // checking to see if slots have a request made or not. Used for changing images
    public boolean Slot1Empty() { return isSlot1Empty; }
    public boolean Slot2Empty() { return isSlot2Empty; }
    public boolean Slot3Empty() { return isSlot3Empty; }

    // TODO: Matthias - when program completely ends, change images in shared preferences back to plus-signs
}

