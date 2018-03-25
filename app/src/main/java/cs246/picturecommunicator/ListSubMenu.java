package cs246.picturecommunicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Landon Campbell
 * @date 3/17/18
 * Displays the chosen category in the previous menu as a list view and adds it to one of the three main containers on the first activity.
 */
public class ListSubMenu extends AppCompatActivity {

    List currentList = new ArrayList<PictureHolder>();
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
        String slot = intent.getStringExtra(MainActivity.EXTRA_SLOT_CHOICE);
        String category = intent.getStringExtra(CategoryMenu.EXTRA_CATEGORY_CHOICE);

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
            hm.put("listview_image",temp.getFilename());







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
    }

    // TODO: Landon -  Create a function to store the image chosen and the category into shared preferences.
}
