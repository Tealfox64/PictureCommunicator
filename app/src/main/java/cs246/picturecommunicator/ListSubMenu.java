package cs246.picturecommunicator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * <h1>ListSubMenu</h1>
 * This is the menu that will display the images to select from in a custom ListView.
 * It is determined by the category being received from the previous activity, and will
 * change the background image of the button slot in Main Activity
 */
public class ListSubMenu extends AppCompatActivity {


    // Tag for debugging
    private static final String TAG = "ListSubMenu";

    // Temporary list of Pictures to be displayed in the list view.
    List currentList = new ArrayList<PictureHolder>();

    // Translator object to choose between English or Spanish
    Translator translator;
    String title;
    String category;
    String slot;
    String language;


    /**
     * <h2>onCreate</h2>
     * This function will set up the list view based on the category being selected.
     * The design format for each row is laid out by mylist.xml.
     * This function also creates an onClickListener for each item that will store the item's
     * data into shared preferences to be read by the Main Activity if selected.
     * @param savedInstanceState Takes in the current state of app.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sub_menu);

        List<HashMap<String, String>> categoryListItems = new ArrayList<HashMap<String, String>>();

        // Receive the intent from the Category Menu
        Intent intent = getIntent();

        // Get the slot number from the previous intent.
        // This will then be passed back to the main activity in order to know which slot to put the selected picture.
        slot = intent.getStringExtra(MainActivity.EXTRA_SLOT_CHOICE);

        // Get the category chosen from the previous intent
        category = intent.getStringExtra(CategoryMenu.EXTRA_CATEGORY_CHOICE);

        language = intent.getStringExtra(MainActivity.EXTRA_LANGUAGE_CHOICE);

        //Get the category list depending on the EXTRA_CATEGORY_CHOICE.
        switch (category) {
            case "Food":
                currentList = MainActivity.foodList;

                break;
            case "Pain":
                currentList = MainActivity.painList;
                break;
            case "Family":
                currentList = MainActivity.familyList;
                break;
            case "Activities":
                currentList = MainActivity.activitiesList;
                break;
        }


        // Putting the title, description, and image in the HashMap/Array List
        // The quantity needs to be the number of items in the category list chosen and not 8.
        for (int i = 0; i < currentList.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            PictureHolder temp = (PictureHolder)currentList.get(i);

            // TODO Set up translator interface with a Translator class

            if (Objects.equals(language, "English"))
                translator = new EnglishTranslator();
            if (Objects.equals(language, "Espanol"))
                translator = new SpanishTranslator();

            hm.put("listview_title",translator.translate(temp));
            hm.put("listview_image",Integer.toString(temp.getFilename()));


            categoryListItems.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.list_image, R.id.title};

        // Creates the adapter using the row view (mylist.xml) and the list of HashMap items.
        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), categoryListItems, R.layout.mylist, from, to);
        ListView androidListView = findViewById(R.id.list);
        // sets the new adapter
        androidListView.setAdapter(simpleAdapter);





        // HOW TO STORE INT IN A SHARED PREFERENCES SLOT
        // Item Click Listener for the listview
        OnItemClickListener itemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                // Getting the Container Layout of the ListView
                LinearLayout linearLayoutParent = (LinearLayout) container;

                // Getting the inner Linear Layout
                LinearLayout linearLayoutChild = (LinearLayout ) linearLayoutParent.getChildAt(1);

                // Getting the Title of the picture clicked on by the user
                TextView label = (TextView) linearLayoutChild.getChildAt(0);

                //Show the user that the correct item was clicked on properly using a toast
                Toast.makeText(getBaseContext(), label.getText().toString(), Toast.LENGTH_SHORT).show();
                title = label.getText().toString();

                //Search the list based on the TextView text and send the image RESID to the MainActivity
                // Compares the title of the picture with the titles of all picture containers in the chosen list
                for (int i = 0; i < currentList.size(); i++) {
                    PictureHolder temp = (PictureHolder)currentList.get(i);
                    String tempTitle;

                    tempTitle = translator.translate(temp);


                    if (Objects.equals(tempTitle, title)) {
                        SharedPreferences sharedPreferences = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor;

                        // Stores the RESID of the selected picture in the correct slot within shared preferences.
                        switch (slot) {
                            case "Slot_1":
                                MainActivity.slot1Category = category;

                                editor = sharedPreferences.edit();
                                editor.putInt(MainActivity.SLOT1_RESID,temp.getFilename());
                                Log.d(TAG,"Slot 1: " + (translator.translate(temp)));

                                editor.apply();

                                break;
                            case "Slot_2":
                                MainActivity.slot2Category = category;
                                editor = sharedPreferences.edit();
                                editor.putInt(MainActivity.SLOT2_RESID,temp.getFilename());

                                editor.apply();

                                break;
                            case "Slot_3":
                                MainActivity.slot3Category = category;
                                editor = sharedPreferences.edit();

                                editor.putInt(MainActivity.SLOT3_RESID,temp.getFilename());
                                editor.apply();


                                break;
                        }
                    }

                }


                Intent intent;
                intent = new Intent(parent.getContext(), MainActivity.class);
                startActivity(intent);

            }
        };

        // Setting the item click listener for the listview
        androidListView.setOnItemClickListener(itemClickListener);


    }

}
