package cs246.picturecommunicator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Picture;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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

    // string for Extra key for intent, public so it can be accessed
    public static final String EXTRA_SLOT_CHOICE = "cs246.picturecommunicator.SLOT_CHOICE";

    public static String FILENAME = "listimages.txt";

    // 4 lists to store picture data
    public List activitiesList = new ArrayList<PictureHolder>();
    public List foodList = new ArrayList<PictureHolder>();
    public List painList = new ArrayList<PictureHolder>();
    public List familyList = new ArrayList<PictureHolder>();


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // read an entire line from a file, expects some kind of input stream
        try(BufferedReader finReader = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)))) {

            // create String outside of scope, and use a boolean expression
            String line;
            PictureHolder picture;
            String filepath;
            String label;
            String category;
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

                category = "";
                for (; i < line.length(); i++)
                {
                    category += line.charAt(i);
                }

                picture = new PictureHolder();
                picture.filename = filepath;
                picture.label = label;
                picture.category = category;

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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;

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

    // TODO: Matthias - when program completely ends, change images in shared preferences back to plus-signs
}

