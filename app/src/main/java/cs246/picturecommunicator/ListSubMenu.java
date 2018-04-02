package cs246.picturecommunicator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
 * @author Landon Campbell
 * @date 3/17/18
 * Displays the chosen category in the previous menu as a list view and adds it to one of the three main containers on the first activity.
 */
public class ListSubMenu extends AppCompatActivity {
    // variables to choose which image to use when request has been made
    public static boolean isSlot1Empty = true;
    public static boolean isSlot2Empty = true;
    public static boolean isSlot3Empty = true;

    List currentList = new ArrayList<PictureHolder>();
    String title;
    String category;
    String slot;

    // Array of strings for ListView Title
    // take in the list from the previous
    String[] listviewTitle = new String[]{
            "ListView Title 1", "ListView Title 2", "ListView Title 3", "ListView Title 4",
            "ListView Title 5", "ListView Title 6", "ListView Title 7", "ListView Title 8",
    };


    int[] listviewImage = new int[]{
            R.drawable.example_image, R.drawable.example_image, R.drawable.example_image, R.drawable.example_image,
            R.drawable.example_image, R.drawable.example_image, R.drawable.example_image, R.drawable.example_image,
    };

    String[] listviewShortDescription = new String[]{
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
    };

    /**
     *
     * @param savedInstanceState Takes in the current state of app.
     * Generates the listview. Uses mylist as the row format.
     * Activity_list
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sub_menu);

        List<HashMap<String, String>> categoryListItems = new ArrayList<HashMap<String, String>>();

        Intent intent = getIntent();
        slot = intent.getStringExtra(MainActivity.EXTRA_SLOT_CHOICE);
        category = intent.getStringExtra(CategoryMenu.EXTRA_CATEGORY_CHOICE);

        //TODO: Landon - Get the category list depending on the EXTRA_CATEGORY_CHOICE.
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


        //putting the title, description, and image in the HashMap/Array List
        // The quantity needs to be the number of itmes in the catergory list chosen and not 8.
        for (int i = 0; i < currentList.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            PictureHolder temp = (PictureHolder)currentList.get(i);

            hm.put("listview_title",temp.getLabel());
            hm.put("listview_image",Integer.toString(temp.getFilename()));







//            hm.put("listview_title", currentList[i]);
//
//            hm.put("listview_image", Integer.toString(listviewImage[i])); hm.put("listview_discription", listviewShortDescription[i]); //I am going to take this out
            categoryListItems.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.list_image, R.id.title};

        //Creates the adapter using the row view (mylist.xml) and the list of HashMap items.
        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), categoryListItems, R.layout.mylist, from, to);
        ListView androidListView = findViewById(R.id.list);
        //sets the new adapter
        androidListView.setAdapter(simpleAdapter);

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

                //TODO: Search the list based on the TextView text and send the image RESID to the MainActivity

                for (int i = 0; i < currentList.size(); i++) {
                    PictureHolder temp = (PictureHolder)currentList.get(i);
                    String tempTitle;
                    tempTitle = temp.getLabel();

                    if (Objects.equals(tempTitle, title)) {
                        SharedPreferences sharedPreferences = MainActivity.sharedpreferences;
                        SharedPreferences.Editor editor;


                        switch (slot) {
                            case "Slot1":
                                MainActivity.slot1Category = category;

                                editor = sharedPreferences.edit();
                                editor.putInt(MainActivity.SLOT1_RESID,temp.getFilename());
                                //MainActivity.slot1RES = temp.getFilename();
                                editor.apply();
                                isSlot1Empty = false;

                            case "Slot2":
                                MainActivity.slot2Category = category;
                                editor = sharedPreferences.edit();
                                editor.putInt(MainActivity.SLOT2_RESID,temp.getFilename());
                                //MainActivity.slot2RES = temp.getFilename();
                                editor.apply();
                                isSlot2Empty = false;

                            case "Slot3":
                                MainActivity.slot3Category = category;
                                editor = sharedPreferences.edit();
                                editor.apply();
                                editor.putInt(MainActivity.SLOT3_RESID,temp.getFilename());
                                //MainActivity.slot3RES = temp.getFilename();
                                isSlot3Empty = false;
                        }
                    }

                }


                Intent intent;
                intent = new Intent(parent.getContext(), MainActivity.class);
                startActivity(intent);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_sub_menu, menu);
        return true;
    }
    // TODO: Landon -  Create a function to store the image chosen and the category into shared preferences.
}
