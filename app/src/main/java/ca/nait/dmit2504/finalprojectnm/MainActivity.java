package ca.nait.dmit2504.finalprojectnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private RecyclerView mPeopleRecycler;
    private PeopleAdapter.RecyclerViewClickListener listener;
    private List<People> peopleList;
    private CoordinatorLayout mLayout;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create an instance of the Menu inflator
        MenuInflater inflater = getMenuInflater();
        // Inflate the menu
        inflater.inflate(R.menu.options_menu, menu);
        // Return true if the menu inflated OK
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = findViewById(R.id.activity_main_CoordinatorLayour);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        androidx.preference.PreferenceManager.setDefaultValues(this,R.xml.root_preferences, false);

        //mLayout.setBackgroundColor(Color.parseColor(prefs.getString("background_colour_pref", "White")));

        FloatingActionButton addNewFloatingButton = findViewById(R.id.floatingActionButton);
        addNewFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNewIntent = new Intent(getApplicationContext(),NewPeopleActivity.class);
                startActivity(addNewIntent);
            }
        });

        mPeopleRecycler = findViewById(R.id.activity_main_recyclerview);
        //mPeopleRecycler.setHasFixedSize(true);

        loadRecycler();



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_preference:
                Intent goToPreferenceIntent = new Intent(this, SettingsActivity.class);
                startActivity(goToPreferenceIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecycler();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
        mLayout.setBackgroundColor(Color.parseColor(prefs.getString("background_colour_pref", "WHITE")));

    }
    private void loadRecycler() {



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPeopleRecycler.setLayoutManager(linearLayoutManager);


        AppDatabase appDB = AppDatabase.getDatabase(getApplicationContext());
        peopleList = appDB.peopleDao().getAll();

        PeopleAdapter peopleAdapter = new PeopleAdapter(peopleList,listener);
        mPeopleRecycler.setAdapter(peopleAdapter);
        setOnClickListener();


    }

    private void setOnClickListener() {
        listener = new PeopleAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                int personId = peopleList.get(position).getId();
                Intent intent = new Intent(getApplicationContext(),EditPeopleActivity.class);
                intent.putExtra("personId", personId);
                startActivity(intent);
            }
        };
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mLayout.setBackgroundColor(Color.parseColor(prefs.getString("background_colour_pref", "WHITE")));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);

    }

}