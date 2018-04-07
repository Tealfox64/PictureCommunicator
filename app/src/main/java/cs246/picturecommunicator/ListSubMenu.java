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
    // variables to choose which image to use when request has been made
    // TODO: These are never used...?
    public static boolean isSlot1Empty = true;
    public static boolean isSlot2Empty = true;
    public static boolean isSlot3Empty = true;

    // Tag for debugging
    private static final String TAG = "ListSubMenu";

    // List to be displayed
    List currentList = new ArrayList<PictureHolder>();
    String title;
    String category;
    String slot;

    // TODO: These are never used...?
    // Array of strings for ListView Title
    // take in the list from the previous
    String[] listviewTitle = new String[]{
            "ListView Title 1", "ListView Title 2", "ListView Title 3", "ListView Title 4",
            "ListView Title 5", "ListView Title 6", "ListView Title 7", "ListView Title 8",
    };

    // TODO: Not used...?
    int[] listviewImage = new int[]{
            R.drawable.example_image, R.drawable.example_image, R.drawable.example_image, R.drawable.example_image,
            R.drawable.example_image, R.drawable.example_image, R.drawable.example_image, R.drawable.example_image,
    };

    // TODO: Also not used...?
    String[] listviewShortDescription = new String[]{
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
    };

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

        Intent intent = getIntent();
        slot = intent.getStringExtra(MainActivity.EXTRA_SLOT_CHOICE);
        category = intent.getStringExtra(CategoryMenu.EXTRA_CATEGORY_CHOICE);

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
        // The quantity needs to be the number of itmes in the catergory list chosen and not 8.
        for (int i = 0; i < currentList.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            PictureHolder temp = (PictureHolder)currentList.get(i);

            hm.put("listview_title",temp.getLabel());
            hm.put("listview_image",Integer.toString(temp.getFilename()));






            // TODO: Is this cleanable?
//            hm.put("listview_title", currentList[i]);
//
//            hm.put("listview_image", Integer.toString(listviewImage[i])); hm.put("listview_discription", listviewShortDescription[i]); //I am going to take this out
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

                // Getting the Country TextView
                TextView label = (TextView) linearLayoutChild.getChildAt(0);

                Toast.makeText(getBaseContext(), label.getText().toString(), Toast.LENGTH_SHORT).show();
                title = label.getText().toString();

                //Search the list based on the TextView text and send the image RESID to the MainActivity

                for (int i = 0; i < currentList.size(); i++) {
                    PictureHolder temp = (PictureHolder)currentList.get(i);
                    String tempTitle;
                    tempTitle = temp.getLabel();

                    if (Objects.equals(tempTitle, title)) {
                        SharedPreferences sharedPreferences = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor;

                        // TODO: There are some commented code snippets that could be cleaned...?
                        switch (slot) {
                            case "Slot_1":
                                MainActivity.slot1Category = category;

                                editor = sharedPreferences.edit();
                                editor.putInt(MainActivity.SLOT1_RESID,temp.getFilename());
                                Log.d(TAG,"Slot 1: " + (temp.getLabel()));
                                //MainActivity.slot1RES = temp.getFilename();
                                editor.apply();
//                                isSlot1Empty = false;

                                break;
                            case "Slot_2":
                                MainActivity.slot2Category = category;
                                editor = sharedPreferences.edit();
                                editor.putInt(MainActivity.SLOT2_RESID,temp.getFilename());
                                //MainActivity.slot2RES = temp.getFilename();
                                editor.apply();
//                                isSlot2Empty = false;

                                break;
                            case "Slot_3":
                                MainActivity.slot3Category = category;
                                editor = sharedPreferences.edit();

                                editor.putInt(MainActivity.SLOT3_RESID,temp.getFilename());
                                editor.apply();
                                //MainActivity.slot3RES = temp.getFilename();
//                                isSlot3Empty = false;

                                break;
                        }
                    }

                }


                Intent intent;
                intent = new Intent(parent.getContext(), MainActivity.class);
                startActivity(intent);
                // TODO: Clean these...?
                // Slot Categories
//                public static String slot1Category;
//                public static String slot2Category;
//                public static String slot3Category;
//
//
//                // Slot ImageRESID
//                public static int slot1RES;
//                public static int slot2RES;
//                public static int slot3RES;
            }
        };

        // Setting the item click listener for the listview
        androidListView.setOnItemClickListener(itemClickListener);


    }
    // TODO: This creates an options menu that is never used, do we still need it?
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_sub_menu, menu);
        return true;
    }
}
