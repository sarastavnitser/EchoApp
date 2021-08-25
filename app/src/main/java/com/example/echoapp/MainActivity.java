package com.example.echoapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.PersistableBundle;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.echoapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import lib.Utils;
import model.UserEntryList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private final UserEntryList mUserEntriesList = new UserEntryList();
    private boolean mShowHistory = true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        com.example.echoapp.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        //setting the list view  and text views
//        ListView entries = (ListView) findViewById(R.id.entries);
        TextView about = findViewById(R.id.about_message);
         EditText enteredMessage = findViewById(R.id.entered_message);

         TextView entries = findViewById(R.id.entries);
        //creating a list adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, mUserEntriesList.getUserEntriesListAsList());

//        entries.setAdapter(adapter);
        binding.fab.setOnClickListener(view -> {
            //when play is hit, the about is changed and the entry is added to the array list
            if (enteredMessage != null) {
                about.setText((enteredMessage.getText().toString()));
                mUserEntriesList.addEntryToList((enteredMessage.getText().toString()));
//                entries.setAdapter(adapter);
                entries.setText(mUserEntriesList.getUserEntriesListAsString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_display_prior_entries) {
            TextView entries = findViewById(R.id.entries);
            toggleMenuItem(item);
            mShowHistory = item.isChecked();
            if(mShowHistory){
                entries.setText(mUserEntriesList.getUserEntriesListAsString());
            }else{
                entries.setText("");
            }

        } else if (id == R.id.action_clear_prior_entries) {
            TextView entries = findViewById(R.id.entries);
            mUserEntriesList.clearUserEntries();
            entries.setText(mUserEntriesList.getUserEntriesListAsString());

        } else if (id == R.id.action_about) {
            Utils.showInfoDialog(MainActivity.this,
                    R.string.about, R.string.about_text);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_display_prior_entries).setChecked(mShowHistory);
        return super.onPrepareOptionsMenu(menu);
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void toggleMenuItem(MenuItem item) {
        item.setChecked(!item.isChecked());

    }


}